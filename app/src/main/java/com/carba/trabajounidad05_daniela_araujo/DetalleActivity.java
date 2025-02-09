package com.carba.trabajounidad05_daniela_araujo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {
    private VideoView videoView; // Vista para mostrar videos
    private WebView webView; // Vista para mostrar contenido web
    private Button botonVolver, botonAudio; // Botones para navegación y controlar audio
    private MediaPlayer mediaPlayer; // Reproductor de audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        TextView txtTitulo = findViewById(R.id.textTitulo);
        TextView txtDescripcion = findViewById(R.id.textDescripcion);
        videoView = findViewById(R.id.videoView);
        webView = findViewById(R.id.webView);
        botonVolver = findViewById(R.id.btnVolver);
        botonAudio = findViewById(R.id.botonAudio);

        // Recupero el objeto MultimediaItem que pasé desde la actividad anterior
        Intent intent = getIntent();
        if (intent != null) {
            MultimediaItem item = (MultimediaItem) intent.getSerializableExtra("item");
            if (item != null) {
                txtTitulo.setText(item.getTitulo());
                txtDescripcion.setText(item.getDescripcion());

                // Manejo según el tipo de contenido multimedia
                if (item.getTipo() == MultimediaItem.TIPO_VIDEO) {
                    // Configuración para reproducir video
                    videoView.setVisibility(View.VISIBLE); // Muestro VideoView
                    videoView.setVideoURI(Uri.parse(item.getUrl()));
                    MediaController mediaController = new MediaController(this); // Controladores para video
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    videoView.setOnPreparedListener(mp -> videoView.start()); // Inicia el video cuando esté preparado

                } else if (item.getTipo() == MultimediaItem.TIPO_AUDIO) {
                    // Configuración para reproducir audio
                    mediaPlayer = MediaPlayer.create(this, Uri.parse(item.getUrl()));
                    botonAudio.setVisibility(View.VISIBLE); // Muestra el botón de audio

                    // Configuro el botón para reproducir o pausar el audio
                    botonAudio.setOnClickListener(v -> {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause(); // Si está reproduciendo, lo pausamos
                            botonAudio.setText("Reproducir");
                        } else {
                            mediaPlayer.start(); // Si no está reproduciendo, lo iniciamos
                            botonAudio.setText("Pausar");
                        }
                    });
                } else if (item.getTipo() == MultimediaItem.TIPO_WEB) {
                    // Configuración para cargar una página web
                    webView.setVisibility(View.VISIBLE); // Muestra WebView
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true); // Habilita JavaScript
                    webView.loadUrl(item.getUrl()); // Carga la URL en el WebView
                }
            }
        }
        botonVolver.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libero los recursos del MediaPlayer cuando la actividad se destruye
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}

