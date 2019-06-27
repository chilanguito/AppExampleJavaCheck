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
import us.gonet.applist.model.ModelList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<ModelList> list = new LinkedList<>();
    private AdapterList adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] arrayString = getResources().getStringArray(R.array.array);

        Arrays.sort(arrayString);


        for (String valor : arrayString) {
            ModelList model = new ModelList(valor, false, 0);
            list.addLast(model);
        }


        adapterList = new AdapterList(list, new AdapterList.CheckList() {
            @Override
            public void click(int position) {
                updatePositions();
                if (!list.get(position).isChecked()) {
                    String value = list.get(position).getName();
                    list.remove(position);
                    ModelList model = new ModelList(value, true, position);
                    list.addLast(model);
                    adapterList.notifyItemMoved(position, list.size() - 1);
                } else {
                    String value = list.get(position).getName();
                    int valor = list.get(position).getPosition();

                    if (valor + 1 == list.size()) {
                        valor = 0;
                    }

                    list.remove(position);
                    ModelList model = new ModelList(value, false, valor);

                    if (valor == 0) {
                        list.addFirst(model);
                    } else {
                        list.add(model);
                    }

                    adapterList.notifyItemMoved(position, valor);
                }
                Collections.sort(list);
                adapterList.notifyDataSetChanged();
            }

            void updatePositions() {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPosition(i);
                }
            }
        }
        );

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
                String newName = data.getStringExtra("NEW");
                ModelList model = new ModelList(newName, false, 0);
                list.addFirst(model);
                Collections.sort(list);
                adapterList.notifyDataSetChanged();
            }
        }
    }
}
