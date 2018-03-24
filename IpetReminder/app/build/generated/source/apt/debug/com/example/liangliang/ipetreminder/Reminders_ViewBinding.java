// Generated code from Butter Knife. Do not modify!
package com.example.liangliang.ipetreminder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Reminders_ViewBinding implements Unbinder {
  private Reminders target;

  @UiThread
  public Reminders_ViewBinding(Reminders target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Reminders_ViewBinding(Reminders target, View source) {
    this.target = target;

    target.mName = Utils.findRequiredViewAsType(source, R.id.pet_name, "field 'mName'", EditText.class);
    target.mBirthday = Utils.findRequiredViewAsType(source, R.id.birthday, "field 'mBirthday'", EditText.class);
    target.genderImage = Utils.findRequiredViewAsType(source, R.id.gender_image, "field 'genderImage'", ImageView.class);
    target.ownerName = Utils.findRequiredViewAsType(source, R.id.pet_owner, "field 'ownerName'", EditText.class);
    target.medicineTab = Utils.findRequiredViewAsType(source, R.id.medicineTab, "field 'medicineTab'", Button.class);
    target.hygieneTab = Utils.findRequiredViewAsType(source, R.id.hygieneTab, "field 'hygieneTab'", Button.class);
    target.exerciseTab = Utils.findRequiredViewAsType(source, R.id.exerciseTab, "field 'exerciseTab'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Reminders target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mName = null;
    target.mBirthday = null;
    target.genderImage = null;
    target.ownerName = null;
    target.medicineTab = null;
    target.hygieneTab = null;
    target.exerciseTab = null;
  }
}
