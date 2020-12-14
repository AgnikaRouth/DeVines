package devines.com.devines20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class UserManual extends AppCompatActivity {

    PDFView book1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        book1 = (PDFView) findViewById(R.id.pdfView);

        book1.fromAsset("manuelutilisateur.pdf").load();
    }

    void setMinZoom(float zoom) {

    }

    void setMidZoom(float zoom) {

    }

    void setMaxZoom(float zoom) {

    }

}
