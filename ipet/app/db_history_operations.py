from .models import ContactRecord, Appointment, Vet
import datetime


'''
This function is used to insert a single contact record.
The function takes in 4 parameters, user, vet_or_clinic, time, and duration respectively.

user is an entry to a pet owner object. (the pet owner involved in this phone call)

clinic_or_vet is an entry to either a vet or a clinic object, the other party involved in this phone call.

time is the time this phone call took place. It should be a datetime.datetime object.

duration is the length of this phone call. It should be a datetime.timedelta object.

No validity check of parameters is provided.
'''


def insert_contact_record(user, vet_or_clinic, time, duration):
	ContactRecord.objects.create(user = user.phone_number, clinic_or_vet = vet_or_clinic.phone_number, time = time,
								 duration = duration)


'''
This function takes in 2 parameters, user and clinic, and returns contact record between the user and the clinic.

user is an entry to a pet owner object, whereas clinic is an entry to a clinic object.

All the contact record between the user and the clinic, the user and all the vets belongs to the clinic would be
returned as the result.

The result is a python list, each list member is also a python list consisting of 3 members.
The first member is the name of the clinic or the vet (depending on whom the user called), the second element is 
the time of the call and the third is the length of the call.
The lists representing the phone calls would be sorted in time order, the most recent one would appear first.
'''


def query_contact_history(user, clinic):
	user = user.phone_number
	vets = list(Vet.objects.filter(clinic = clinic))

	q_set1 = ContactRecord.objects.none()
	for vet in vets:
		local_q_set = ContactRecord.objects.filter(user = user, clinic_or_vet = vet.phone_number)
		q_set1 = q_set1 | local_q_set

	clinic = clinic.phone_number
	q_set2 = ContactRecord.objects.filter(user = user, clinic_or_vet = clinic)

	q_set = q_set1 | q_set2
	q_set = list(q_set.order_by('-time'))

	if len(q_set) == 0:
		return False

	ret = []
	for records in q_set:
		try:
			ret.append([records.clinic_or_vet.clinic_name.name, records.time, records.duration])
		except:
			ret.append([records.clinic_or_vet.vet_name.name, records.time, records.duration])
	return ret


'''
This function can be used to insert an appointment.
It takes in 3 parameters, user, clinic and time.

user is an entry to a pet owner object. (the pet owner who made this appointment)

clinic is an entry to a clinic object, representing the clinic involved in this action.
It has to be a verified clinic, otherwise a PermissionError would be raised.

time is the time of this appointment.
'''


def insert_appointment(user, clinic, time):
	if not clinic.isVerified:
		raise PermissionError('The clinic is not verified')
	Appointment.objects.create(user = user, clinic = clinic, time = time)


'''
This function takes in 2 parameters, user and clinic, and returns all the appointments between the user and the clinic.

user is an entry to a pet owner object, whereas clinic is an entry to a clinic object.

The result is a tuple of two python lists, both are sorted in the time order (the most recent one comes first).
The first list represents all the appointments not happened yet, whereas the second represents the appointments happened.

The member lists of the two tuple members share the same format, all of which are purely datetime.datetime objects.
'''


def query_appointment_w_clinic(user, clinic):
	current_time = datetime.datetime.now()

	q_set = Appointment.objects.filter(user = user, clinic = clinic)
	q_set1 = q_set.filter(time__gte = current_time)  # going to happen
	q_set2 = q_set.filter(time__lt = current_time)  # happened already

	q_set1 = list(q_set1.order_by('time'))
	q_set2 = list(q_set2.order_by('-time'))

	# ret1 and ret 2 have to be separated in different lines, otherwise they are different alias of the same object
	ret1 = []
	ret2 = []

	for event in q_set1:
		ret1.append(event.time)
	for event in q_set2:
		ret2.append(event.time)

	return ret1, ret2


'''
This function takes in 1 parameter, user, and returns all the past appointments made by this user.

user is an entry to a pet owner object.

The result is a python list, whose members are lists of two members,
sorted in the time order (the most recent one comes first).
The first element of each nested list is the name of the clinic, the second is the time of the appointment.
'''


def query_appointment_history(user):
	current_time = datetime.datetime.now()

	q_set = Appointment.objects.filter(user = user, time__lt = current_time)
	q_set = list(q_set.order_by('-time'))

	ret = []
	for event in q_set:
		ret.append([event.clinic.name, event.time])

	return ret


'''
This function takes in 1 parameter, user, and returns all the future appointments made by this user.

user is an entry to a pet owner object.

The result is a python list, whose members are lists of two members,
sorted in the time order (the most recent one comes first).
The first element of each nested list is the name of the clinic, the second is the time of the appointment.
'''


def query_future_appointment(user):
	current_time = datetime.datetime.now()

	q_set = Appointment.objects.filter(user = user, time__gte = current_time)
	q_set = list(q_set.order_by('time'))

	ret = []
	for event in q_set:
		ret.append([event.clinic.name, event.time])

	return ret
