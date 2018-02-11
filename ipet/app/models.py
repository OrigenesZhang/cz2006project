from django.db import models


class PhoneNumber(models.Model):
	phone_number = models.CharField(max_length = 16)


class User(models.Model):
	name = models.CharField(max_length = 32)
	phone_number = models.OneToOneField(PhoneNumber, on_delete = models.CASCADE)
	password = models.CharField(max_length = 16)
	registration_date = models.DateField()

	class Meta:
		abstract = True


class PetOwner(User):
	breed = models.CharField(max_length = 16)
	birthday = models.DateField(null = True, blank = False)


class Clinic(User):
	address = models.CharField(max_length = 128)
	license = models.CharField(max_length = 64)
	isVerified = models.BooleanField()


class Vet(User):
	clinic = models.ForeignKey(Clinic, on_delete = models.CASCADE)
	isVerified = models.BooleanField()


class Reminder(models.Model):
	user = models.ForeignKey(PetOwner, on_delete = models.CASCADE)
	name = models.CharField(max_length = 128)
	remarks = models.TextField(max_length = 1024)
	remain_times = models.IntegerField(default = 1 << 20)
	next_time = models.DateTimeField()
	period = models.DurationField()
	type = models.SmallIntegerField()


class SingleReminder(models.Model):
	link = models.ForeignKey(Reminder, on_delete = models.CASCADE)
	user = models.ForeignKey(PetOwner, on_delete = models.CASCADE, null = True)
	type = models.SmallIntegerField()
	time = models.DateTimeField()


class Post(models.Model):
	user = models.ForeignKey(PhoneNumber, on_delete = models.CASCADE)
	time = models.DateTimeField()
	title = models.CharField(max_length = 128)
	content = models.TextField(max_length = 4096)
	reports = models.ManyToManyField(PhoneNumber, related_name = 'reports_reports')
	likes = models.ManyToManyField(PhoneNumber, related_name = 'likes_likes')


class Reply(models.Model):
	user = models.ForeignKey(PhoneNumber, on_delete = models.CASCADE)
	thread = models.ForeignKey(Post, on_delete = models.CASCADE)
	time = models.DateTimeField()
	content = models.TextField(max_length = 2048)
	reports = models.ManyToManyField(PhoneNumber, related_name = 'reports')
	likes = models.ManyToManyField(PhoneNumber, related_name = 'likes')
