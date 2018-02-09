from django.db import models
import datetime
from django.utils import timezone


class User(models.Model):
	name = models.CharField(max_length = 32)
	phone_number = models.CharField(max_length = 16)
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