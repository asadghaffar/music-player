package com.blinkedge.musciplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blinkedge.musciplayer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingActivity extends AppCompatActivity {

    private LinearLayout rateAppLinear;
    private LinearLayout shareAppLinear;
    private LinearLayout privacyPolicyLinear;
    private LinearLayout moreAppsLinear;
    private LinearLayout exitLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        id();
        onClick();

    }

    private void onClick() {
        rateAppLinear.setOnClickListener((View.OnClickListener) view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tencent.ig&hl=en"));
            startActivity(intent);
        });

        shareAppLinear.setOnClickListener((View.OnClickListener) view -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +
                        SettingActivity.this.getPackageName() + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        privacyPolicyLinear.setOnClickListener((View.OnClickListener) view ->
                Toast.makeText(SettingActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show());

        exitLinear.setOnClickListener((View.OnClickListener) view -> {
            new MaterialAlertDialogBuilder(SettingActivity.this).setIcon(R.drawable.ic_exit_alert_dialog_icon)
                    .setTitle("Are you sure to exit")
                    .setCancelable(false)
                    .setPositiveButton("yes", (dialogInterface, i) -> SettingActivity.this.finish())
                    .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss()).show();
        });

        moreAppsLinear.setOnClickListener((View.OnClickListener) view ->
                Toast.makeText(SettingActivity.this, "More Apps Shown", Toast.LENGTH_SHORT).show());

    }

    private void id() {
        moreAppsLinear = findViewById(R.id.moreAppsLinear);
        rateAppLinear = findViewById(R.id.rateAppLinear);
        shareAppLinear = findViewById(R.id.shareAppLinear);
        privacyPolicyLinear = findViewById(R.id.privacyPolicyLinear);
        exitLinear = findViewById(R.id.exitLinear);

    }
}
