package com.carba.trabajounidad05_daniela_araujo;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // Vista para mostrar la lista de ítems multimedia
    private MultimediaAdapter adapter; // Adaptador para gestionar los ítems en el RecyclerView
    private List<MultimediaItem> multimediaList; // Lista de ítems multimedia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializo el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Lista vertical
        multimediaList = new ArrayList<>();

        // Agrego ítems multimedia (video, audio, web) a la lista
        multimediaList.add(new MultimediaItem("Video 1", "android.resource://" + getPackageName() + "/" + R.raw.mar, MultimediaItem.TIPO_VIDEO, "Vista relajante del mar.", R.drawable.video));
        multimediaList.add(new MultimediaItem("Video 2", "android.resource://" + getPackageName() + "/" + R.raw.atardecer, MultimediaItem.TIPO_VIDEO, "Un atardecer impresionante.", R.drawable.video));
        multimediaList.add(new MultimediaItem("Audio 1", "android.resource://" + getPackageName() + "/" + R.raw.relajacion, MultimediaItem.TIPO_AUDIO, "Sonidos naturales para relajarte.", R.drawable.altavoz));
        multimediaList.add(new MultimediaItem("Audio 2", "android.resource://" + getPackageName() + "/" + R.raw.estudio, MultimediaItem.TIPO_AUDIO, "Sonidos ideales para estudiar.", R.drawable.altavoz));
        multimediaList.add(new MultimediaItem("Página Web 1", "https://www.google.com", MultimediaItem.TIPO_WEB, "Busca información fácilmente en Google.", R.drawable.www));
        multimediaList.add(new MultimediaItem("Página Web 2", "https://www.instagram.com", MultimediaItem.TIPO_WEB, "Explora y comparte en Instagram.", R.drawable.www));

        // Configuro el adaptador y lo asigno al RecyclerView
        adapter = new MultimediaAdapter(this, multimediaList);
        recyclerView.setAdapter(adapter);
    }
}
