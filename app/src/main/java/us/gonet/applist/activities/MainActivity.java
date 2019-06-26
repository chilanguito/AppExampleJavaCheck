package us.gonet.applist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import us.gonet.applist.R;
import us.gonet.applist.adapter.AdapterList;

public class MainActivity extends AppCompatActivity {

    LinkedList<ModeloLista> lista = new LinkedList<>();
    AdapterList adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] arreglo = getResources().getStringArray(R.array.array);

        Arrays.sort(arreglo);


        for (String valor : arreglo) {
            ModeloLista model = new ModeloLista(valor, false);
            lista.addLast(model);
        }


        adapterList = new AdapterList(lista, new AdapterList.CheckList() {
            @Override
            public void click(int position) {
                if (!lista.get(position).isChecked()){
                    String value = lista.get(position).getNombre();
                    lista.remove(position);
                    ModeloLista model = new ModeloLista(value, true);
                    lista.addLast(model);
                 //   lista.get(lista.size()-1).setChecked(true);
                    adapterList.notifyItemMoved(position,lista.size()-1);
                }
                Collections.sort(lista);
                lista.get(position).setChecked(false);
                adapterList.notifyDataSetChanged();
                Collections.sort(lista);
            }
        });

        recyclerView.setAdapter(adapterList);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNewItem.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                String nuevo = data.getStringExtra("NEW");
                ModeloLista model = new ModeloLista(nuevo, false);
                lista.add(model);
                Collections.sort(lista);
                adapterList.notifyDataSetChanged();
            }
        }
    }
}
