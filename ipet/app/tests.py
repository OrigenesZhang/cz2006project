from django.test import TestCase
from .db_user_operations import insert_user
from .db_user_operations import query_user
import datetime


class RegistrationTest(TestCase):

	def test_pet_owner(self):
		insert_user("Zhang Bin", "1234567", "12345678", 0, info = ["dog", datetime.date.today()])

		res = query_user("1234567")
		self.assertEqual(res.name, "Zhang Bin")
		self.assertEqual(res.phone_number, "1234567")
		self.assertEqual(res.password, "12345678")
		self.assertEqual(res.breed, "dog")
		self.assertEqual(res.birthday, datetime.date.today())

		try:
			insert_user("Zhang Bin", "1234567", "12345678", 0, info = ["dog", datetime.date.today()])
			raise Exception
		except FileExistsError:
			pass

		insert_user("Origenes", "12345678", "12345679", 0, info = ["cat", datetime.date.today()])

		res = query_user("1234567")
		self.assertEqual(res.name, "Zhang Bin")
		self.assertEqual(res.phone_number, "1234567")
		self.assertEqual(res.password, "12345678")
		self.assertEqual(res.breed, "dog")
		self.assertEqual(res.birthday, datetime.date.today())

		res = query_user("12345678")
		# print(res)
		self.assertEqual(res.name, "Origenes")
		self.assertEqual(res.phone_number, "12345678")
		self.assertEqual(res.password, "12345679")
		self.assertEqual(res.breed, "cat")
		self.assertEqual(res.birthday, datetime.date.today())

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

	def test_verification(self):
		self.test_vet()

		from .db_user_operations import verify
		from django.core.exceptions import ObjectDoesNotExist

		try:
			verify("123456")
			raise RuntimeError
		except ObjectDoesNotExist:
			pass

		verify("12345678")
		verify("1234567")

		try:
			verify("1234567")
			raise RuntimeError
		except PermissionError:
			pass


class ReminderTest(TestCase):

	def test_reminder_insertion(self):
		reg = RegistrationTest()
		RegistrationTest.test_pet_owner(reg)

		from .db_reminder_operations import insert_reminder
		entry = query_user("1234567")
		# print(datetime.datetime.now()+datetime.timedelta(days = 1))
		# Test medication reminder
		insert_reminder(entry.pk, "MedTest", "No remarks", [False, datetime.datetime.now()+datetime.timedelta(days = 3)], datetime.datetime.now()+datetime.timedelta(days = 1), 3, 0)
		# Test hygiene reminder
		insert_reminder(entry.pk, "HgTest", "No remarks", [True], datetime.datetime.now()+datetime.timedelta(days = 2), 4, 1)
		# Test exercise reminder
		insert_reminder(entry.pk, "ExTest", "", [False, datetime.datetime.now()+datetime.timedelta(days = 5)], datetime.datetime.now()+datetime.timedelta(hours = 12), 0.5, 2)

		from .models import Reminder
		entry = Reminder.objects.get(name = "MedTest")
		print(entry.name, entry.remarks, entry.next_time, entry.period, entry.remain_times, entry.type)
		entry = Reminder.objects.get(name = "HgTest")
		print(entry.name, entry.remarks, entry.next_time, entry.period, entry.remain_times, entry.type)
		entry = Reminder.objects.get(name = "ExTest")
		print(entry.name, entry.remarks, entry.next_time, entry.period, entry.remain_times, entry.type)

	def test_single_reminder(self):
		self.test_reminder_insertion()

		entry = query_user("1234567")

		from .db_reminder_operations import query_reminder
		flags, ret = query_reminder(entry)
		for items in ret:		# Dangerous practice. Do not use unless you are sure it won't crash
			print(items.link.name, items.type, items.time)
		print()

		from .db_reminder_operations import update_reminder
		for items in ret:
			update_reminder(items)

		entry = query_user("1234567")

		flags, ret = query_reminder(entry)
		for i in range(3):		# Better way to print the result
			if flags[i]:
				print(ret[i].link.name, ret[i].type, ret[i].time)
