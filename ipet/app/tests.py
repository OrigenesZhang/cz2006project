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
		from .db_pet_operations import query_pet
		from .models import Pet

		b_day = datetime.date(year = 2017, month = 4, day = 3)

		modify_pet_info(name = "ThisIsAName", owner_name = "Origenes", birthday = b_day)
		ans = query_pet(name = "ThisIsAName", owner_name = "Origenes")
		self.assertEqual(ans, ["Dog", 0, b_day, None, None])

		year, month, day = get_pet_age(birthday = b_day)
		self.assertEqual(year, 1)
		self.assertEqual(month, 0)
		self.assertEqual(day, 1)

		delete_pet(name = "ThisIsAName", owner_name = "Zhang Bin")

		query_pet(name = "ThisIsAName", owner_name = "Origenes")
		try:
			query_pet(name = "ThisIsAName", owner_name = "Zhang Bin")
			raise RuntimeError("Deletion Error")
		except Pet.DoesNotExist:
			pass
