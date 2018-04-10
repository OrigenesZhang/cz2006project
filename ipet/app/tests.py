from django.test import TestCase
from django.core.exceptions import ObjectDoesNotExist
import datetime


class UserTest(TestCase):

	def test_user_registration(self):

		from .db_user_operations import insert_user, query_user
		insert_user(name = "Zhang Bin", password = "1234567", phone_number = "1234567")

		try:
			insert_user(name = "Zhang Bin", password = "12345678", phone_number = "12345678")
			raise FileExistsError("The name is already taken")
		except FileExistsError:
			pass

		insert_user("Origenes", password = "1234567", phone_number = "12345678")

		try:
			insert_user(name = "zhang bin", phone_number = "12345678", password = "--------")
			raise FileExistsError("The phone number is already taken")
		except FileExistsError:
			pass

		phone_number, name, password = query_user("1234567", False)
		self.assertEqual(phone_number, "1234567")
		self.assertEqual(name, "Zhang Bin")
		self.assertEqual(password, "1234567")

		phone_number, name, password = query_user("Origenes", True)
		self.assertEqual(phone_number, "12345678")
		self.assertEqual(password, "1234567")
		self.assertEqual(name, "Origenes")

		try:
			query_user("Origenes", False)
			raise SystemError("Checking failed")
		except ObjectDoesNotExist:
			pass


class PetTest(TestCase):

	def test_pet_insertion(self):

		from .db_pet_operations import insert_pet, query_pet
		from .models import Pet

		userTest = UserTest()
		userTest.test_user_registration()

		insert_pet(name = "ThisIsAName", owner_name = "Origenes", breed = "Dog")

		try:
			query_pet(name = "ThisIsAName", owner_name = "Zhang Bin")
			raise RuntimeError("Query Pet Error")
		except Pet.DoesNotExist:
			pass

		ans = query_pet(name = "ThisIsAName", owner_name = "Origenes")
		self.assertEqual(len(ans), 5)
		self.assertEqual(ans, ["Dog", 0, None, None, None])

		try:
			insert_pet(name = "Name", owner_name = "origenes", breed = "Dog")
			raise RuntimeError("Insert Pet Error")
		except ObjectDoesNotExist:
			pass

	def test_pet_modification(self):

		self.test_pet_insertion()

		from .db_pet_operations import delete_pet, get_pet_age, modify_pet_info
		from .db_pet_operations import query_pet, insert_pet
		from .models import Pet

		b_day = datetime.date(year = 2017, month = 4, day = 3)

		modify_pet_info(name = "ThisIsAName", owner_name = "Origenes", birthday = b_day)
		ans = query_pet(name = "ThisIsAName", owner_name = "Origenes")
		self.assertEqual(ans, ["Dog", 0, b_day, None, None])

		year, month= get_pet_age(birthday = b_day)
		self.assertEqual(year, 1)
		self.assertEqual(month, 0)

		insert_pet(name = "ThisIsAName", owner_name = "Zhang Bin", breed = "this")
		delete_pet(name = "ThisIsAName", owner_name = "Zhang Bin")

		query_pet(name = "ThisIsAName", owner_name = "Origenes")
		try:
			query_pet(name = "ThisIsAName", owner_name = "Zhang Bin")
			raise RuntimeError("Deletion Error")
		except Pet.DoesNotExist:
			pass


# since this part of code is mostly remained unchanged, the reliability relies on the previous version of testing
# at least for now...
class ReminderTest(TestCase):

	def testing_reminder(self):
		return True


class ClinicTest(TestCase):

	def test_clinic(self):
		from .db_clinic_operations import insert_clinic, query_clinic
		insert_clinic(name = "this is a name", geo_hash = "91808urjo")
		insert_clinic(name = "this is a name", geo_hash = "fwifwjilwe")
		insert_clinic(name = "1", geo_hash = "76899123")
		insert_clinic(name = "2", geo_hash = "67i187481oh")

		res = query_clinic("this is a name")
		self.assertEqual(res[1], "fwifwjilwe")

	def test_clinic_rep(self):
		self.test_clinic()

		from .db_clinic_operations import insert_clinic_rep, query_clinic_rep, query_clinic
		from .models import Clinic

		try:
			insert_clinic_rep(name = "rep name", clinic_name = "this is not a name", password = "fwojfa", phone_number = "fhwkuhfku")
			raise RuntimeError('Insertion Error')
		except Clinic.DoesNotExist:
			pass

		ret = query_clinic(name = "this is a name")
		self.assertEqual(ret[2], None)

		insert_clinic_rep(name = "rep name", clinic_name = "this is a name", password = "123456", phone_number = "1234567")

		ret = query_clinic_rep(["rep name", None, None, None])
		self.assertEqual(ret, ["rep name", "this is a name", "123456", "1234567"])

		ret = query_clinic(name = "this is a name")
		self.assertNotEqual(ret[2], None)


class RateTest(TestCase):

	def test_rating(self):
		ClinicT = ClinicTest()
		ClinicT.test_clinic()

		from .lazy_tag import init, lazy_table
		from .models import Clinic
		init()

		for key, val in lazy_table.items():
			self.assertEqual(val, True)

		from .db_rating_operations import insert_rate, query_rate
		from .db_user_operations import insert_user

		for i in range(1, 10):
			insert_user(str(i), str(i * 123456), str(i * 123456))

		insert_rate(user_name = "3", clinic_name = "2", score = 5, day = datetime.date.today() - datetime.timedelta(days = 1))
		try:
			insert_rate(user_name = "3", clinic_name = "2", score = 4, day = datetime.date.today() - datetime.timedelta(days = 1))
			raise RuntimeError('Insertion not checked')
		except PermissionError:
			pass

		q2 = query_rate("2")
		self.assertLess(q2, 5.001)
		self.assertGreater(q2, 4.999)

		insert_rate(user_name = "5", clinic_name = "2", score = 4, day = datetime.date.today() - datetime.timedelta(days = 1))

		q2 = query_rate("2")
		self.assertLess(q2, 4.501)
		self.assertGreater(q2, 4.499)
		# obj2 = Clinic.objects.get(name = "2")
		# print(obj2.sum, obj2.cnt)

		from .db_rating_operations import sieve_rating
		from .lazy_tag import update_all

		D = datetime.date.today() - datetime.timedelta(days = 1)
		insert_rate(user_name = "3", clinic_name = "1", score = 2, day = D)
		insert_rate(user_name = "7", clinic_name = "1", score = 4, day = D)
		update_all()

		# obj2 = Clinic.objects.get(name = "2")
		# print(obj2.sum, obj2.cnt)

		# q1 = query_rate("2")
		# print("DEBUG***", q1)
		insert_rate(user_name = "8", clinic_name = "2", score = 2, day = D)

		# obj2 = Clinic.objects.get(name = "2")
		# print(obj2.sum, obj2.cnt)

		# q1 = query_rate("1")
		# q2 = query_rate("2")
		# print("DEBUG***")
		# print(q1, q2)


		update_all()

		# obj2 = Clinic.objects.get(name = "2")
		# print(obj2.sum, obj2.cnt)
		#
		# obj1 = Clinic.objects.get(name = "1")
		# print(obj1.sum, obj1.cnt)
		# clinic 1: rating = 3
		# clinic 2: rating = 3.67

		res = sieve_rating(3.5)
		self.assertEqual(len(res), 1)
		self.assertEqual(res[0][0], "2")

		res = sieve_rating(4)
		self.assertEqual(len(res), 0)
