# Generated by Django 2.0.1 on 2018-04-09 10:16

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0019_auto_20180404_1327'),
    ]

    operations = [
        migrations.AddField(
            model_name='clinic',
            name='cnt',
            field=models.IntegerField(default=0),
        ),
        migrations.AddField(
            model_name='clinic',
            name='sum',
            field=models.BigIntegerField(default=0),
        ),
    ]
