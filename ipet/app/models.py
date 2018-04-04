from django.db import models


class User(models.Model):
	name = models.CharField(max_length = 32, unique = True)
	password = models.CharField(max_length = 16)
	phone_number = models.CharField(max_length = 16, unique = True)


class Pet(models.Model):
	name = models.CharField(max_length = 32)
	owner = models.ForeignKey(User, on_delete = models.CASCADE)
	breed = models.CharField(max_length = 32)
	birthday = models.DateField(null = True)
	location = models.CharField(max_length = 128, null = True)
	gender = models.SmallIntegerField(default = 0)
	weight = models.FloatField(null = True)

	class Meta:
		unique_together = ('name', 'owner', )


class Reminder(models.Model):
	user = models.ForeignKey(User, on_delete = models.CASCADE)
	isDeleted = models.BooleanField(default = False)
	name = models.CharField(max_length = 128)
	remarks = models.TextField(max_length = 1024)
	remain_times = models.IntegerField(default = 1 << 20)
	next_time = models.DateTimeField()
	period = models.DurationField()
	type = models.SmallIntegerField()


class SingleReminder(models.Model):
	link = models.ForeignKey(Reminder, on_delete = models.CASCADE)
	isDeleted = models.BooleanField(default = False)
	user = models.ForeignKey(User, on_delete = models.CASCADE, null = True)
	type = models.SmallIntegerField()
	time = models.DateTimeField()


class Clinic(models.Model):
	name = models.CharField(max_length = 128, unique = True)
	geo_hash = models.CharField(max_length = 32, unique = True, default = "")


class ClinicRep(models.Model):
	name = models.CharField(max_length = 128, unique = True)
	clinic = models.OneToOneField(Clinic, on_delete = models.CASCADE, null = True, related_name = 'clinic_rep')
	password = models.CharField(max_length = 16)
	phone_number = models.CharField(max_length = 16, unique = True)


class Rate(models.Model):
	user = models.ForeignKey(User, on_delete = models.CASCADE)
	clinic = models.ForeignKey(Clinic, on_delete = models.CASCADE)
	date = models.DateField()
	score = models.SmallIntegerField()
	comment = models.TextField(max_length = 2048, null = True)

	class Meta:
		unique_together = ('user', 'clinic', 'date', )


class PromoTips(models.Model):
	abstract = models.CharField(max_length = 128, blank = True, null = True)
	title = models.CharField(max_length = 128)
	content = models.TextField(max_length = 4096)
	time = models.DateTimeField()
	user = models.ForeignKey(ClinicRep, on_delete = models.CASCADE)
	type = models.BooleanField()
