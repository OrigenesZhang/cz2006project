from django.test import TestCase
from .db_user_operations import insert_user
from .db_user_operations import query_user


class RegistrationTest(TestCase):

	def test_pet_owner(self):
		insert_user("Zhang Bin", "1234567", "12345678", 0, info = ["dog", 20])

		res = query_user("1234567")
		self.assertEqual(res.name, "Zhang Bin")
		self.assertEqual(res.phone_number, "1234567")
		self.assertEqual(res.password, "12345678")
		self.assertEqual(res.breed, "dog")
		self.assertEqual(res.age, 20)

		try:
			insert_user("Zhang Bin", "1234567", "12345678", 0, info = ["dog", 20])
			raise Exception
		except FileExistsError:
			pass

		insert_user("Origenes", "12345678", "12345679", 0, info = ["cat", 5])

		res = query_user("1234567")
		self.assertEqual(res.name, "Zhang Bin")
		self.assertEqual(res.phone_number, "1234567")
		self.assertEqual(res.password, "12345678")
		self.assertEqual(res.breed, "dog")
		self.assertEqual(res.age, 20)

		res = query_user("12345678")
		# print(res)
		self.assertEqual(res.name, "Origenes")
		self.assertEqual(res.phone_number, "12345678")
		self.assertEqual(res.password, "12345679")
		self.assertEqual(res.breed, "cat")
		self.assertEqual(res.age, 5)

		# add your test here

	def test_clinic(self):
		insert_user("Origenes", "12345678", "12345679", 1, info = ["Hall 15 NTU", "EKLIJQWLF"])

		res = query_user("12345678")
		# print(res)
		self.assertEqual(res.name, "Origenes")
		self.assertEqual(res.address, "Hall 15 NTU")
		self.assertEqual(res.license, "EKLIJQWLF")
		self.assertEqual(res.isVerified, False)

		try:
			insert_user("Zhang Bin", "12345678", "123456789", 0, info = ["dog", 2])
			raise RuntimeError
		except FileExistsError:
			pass

		# add your test here

	def test_vet(self):
		try:
			insert_user("Origenes", "12345678", "12345679", 2, info = ["Fake clinic"])
			raise RuntimeError
		except ValueError:
			pass

		insert_user("Good clinic", "12345678", "12345679", 1, info = ["Hall 15 NTU", "EKLIJQWLF"])

		insert_user("Origenes", "1234567", "12345679", 2, info = ["Good clinic"])

		res = query_user("1234567")
		self.assertEqual(res.name, "Origenes")
		self.assertEqual(res.isVerified, False)

		# add your test here
