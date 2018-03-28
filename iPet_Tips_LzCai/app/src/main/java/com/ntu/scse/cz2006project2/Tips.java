package com.ntu.scse.cz2006project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Tips extends AppCompatActivity {

    ViewGroup tipContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tipContainer = findViewById(R.id.tipContainer);
//        tipTag = findViewById(R.id.tipTag);


    }


    public void buttonNewTip(View view) {

        LinearLayout newMessage = new LinearLayout(this);
        newMessage.setOrientation(LinearLayout.HORIZONTAL);

        ImageView newImage = new ImageView(this);
        newImage.setImageResource(R.mipmap.ic_launcher);
        int img_h = 80;
        int img_w = 100;
        int img_hpd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, img_h, getResources().getDisplayMetrics());
        int img_wpd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, img_w, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams vglp =
                new ViewGroup.LayoutParams(img_wpd, img_hpd);
        newImage.setLayoutParams(vglp);
        newMessage.addView(newImage);

        LinearLayout tipsContent = new LinearLayout(this);
        tipsContent.setOrientation(LinearLayout.VERTICAL);// vertically for text

        TextView newTitle = new TextView(this);
        newTitle.setText(R.string.tip_title);
        tipsContent.addView(newTitle);

        TextView newAbstract = new TextView(this);
        newAbstract.setText(R.string.lorem_ipsum);
        newAbstract.setMaxLines(3);
        newAbstract.setEllipsize(TextUtils.TruncateAt.END);
        tipsContent.addView(newAbstract);

        newMessage.addView(tipsContent);
        LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        // Set TextView layout margin 25 pixels to all side
        // Left Top Right Bottom Margin
        lllp.setMargins(8,16,8,16);
        newMessage.setLayoutParams(lllp);

        tipContainer.addView(newMessage);
    }


}
