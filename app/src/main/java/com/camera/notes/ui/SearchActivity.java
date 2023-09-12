package com.camera.notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Dao;

import android.os.Bundle;


import com.camera.notes.databinding.ActivitySearchBinding;
import com.camera.notes.ui.adapter.NoteAdapterRecycler;
import com.camera.notes.ui.dao.NoteDao;
import com.camera.notes.ui.database.NoteDatabase;
import com.camera.notes.ui.enteties.Note;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements NoteAdapterRecycler.OnItemClick {
    private ActivitySearchBinding binding;
    private NoteAdapterRecycler noteAdapterRecycler;
    private List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        noteAdapterRecycler = new NoteAdapterRecycler(noteList, this, this);
        binding.recyclerView.setAdapter(noteAdapterRecycler);

        NoteDao noteDoa;
        NoteDatabase noteDatabase = NoteDatabase.getNoteDatabase(getApplicationContext());
        noteDoa = noteDatabase.noteDOA();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                noteList.clear();

                NoteDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String q = "%" + query + "%";
                        List list = noteDoa.searchNote(q);
                        noteList.addAll(list);

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    @Override
    public void onClick(Note note, int postion) {

    }
}