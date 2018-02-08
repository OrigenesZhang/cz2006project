from django.db import models


class User(models.Model):
	name = models.CharField(max_length = 32)
	phone_number = models.CharField(max_length = 16)
	password = models.CharField(max_length = 16)

	class Meta:
		abstract = True


class PetOwner(User):
	breed = models.CharField(max_length = 16)
	age = models.IntegerField()


class Clinic(User):
	address = models.CharField(max_length = 128)
	license = models.CharField(max_length = 64)
	isVerified = models.BooleanField()


class Vet(User):
	clinic = models.ForeignKey(Clinic, on_delete = models.CASCADE)
	isVerified = models.BooleanField()


