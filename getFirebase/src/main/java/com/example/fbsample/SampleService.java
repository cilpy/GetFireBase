package com.example.fbsample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Service
public class SampleService {
	private FirebaseDatabase db;
	private List<User> userList = Collections.emptyList();
		
	@PostConstruct
	public void init() {
		db = FirebaseDatabase.getInstance(FirebaseApp.getInstance());
		
		db.getReference("/users").addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				List<User> newList = new ArrayList<>();
				for(DataSnapshot ds : snapshot.getChildren()) {
					newList.add(User.builder()
							.id(ds.getKey())
							.age(ds.child("age").getValue(Integer.class))
							.name(ds.child("name").getValue(String.class))
							.build());
				}
				userList = newList;
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public List<User> getUserList(){
		return userList;
	}
	
}
