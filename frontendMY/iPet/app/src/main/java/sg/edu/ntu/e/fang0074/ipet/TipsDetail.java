package sg.edu.ntu.e.fang0074.ipet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TipsDetail extends AppCompatActivity {

    TextView tipTitle, tipContent;
    ImageView tipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);

        tipTitle = findViewById(R.id.tipDetailTitle);
        tipContent = findViewById(R.id.tipDetailContent);
        tipImage = findViewById(R.id.tipDetailImage);


        tipTitle.setText(R.string.tip_detail_title);
        tipImage.setImageResource(R.mipmap.ic_launcher);

        tipContent.setText(R.string.lorem_ipsum);
        tipContent.setEllipsize(TextUtils.TruncateAt.END);


    }
}
