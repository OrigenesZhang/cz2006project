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
