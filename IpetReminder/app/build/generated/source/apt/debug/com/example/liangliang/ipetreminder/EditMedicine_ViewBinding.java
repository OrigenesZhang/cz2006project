// Generated code from Butter Knife. Do not modify!
package com.example.liangliang.ipetreminder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditMedicine_ViewBinding implements Unbinder {
  private EditMedicine target;

  @UiThread
  public EditMedicine_ViewBinding(EditMedicine target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditMedicine_ViewBinding(EditMedicine target, View source) {
    this.target = target;

    target.mStartDate = Utils.findRequiredViewAsType(source, R.id.selectStartDate, "field 'mStartDate'", TextView.class);
    target.freqSpinner = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'freqSpinner'", Spinner.class);
    target.dosageTv = Utils.findRequiredViewAsType(source, R.id.dosage, "field 'dosageTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditMedicine target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mStartDate = null;
    target.freqSpinner = null;
    target.dosageTv = null;
  }
}
