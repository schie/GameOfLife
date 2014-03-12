package com.example.csci_5370_assignment_4;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button 		runButton, 
						runOnceButton, 
						runTwentyButton;
	private GameView 	gameView;
	private GameEditor	gameEditor;
	private Thread 		thread;
	private Handler 	mainHandler;
	private int 		cntr = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 mainHandler = new Handler();
		
		// instantiate View objects
		runButton = (Button) findViewById(R.id.runButton);
		runOnceButton = (Button) findViewById(R.id.run1Button);
		runTwentyButton = (Button) findViewById(R.id.run20Button);
		gameView = (GameView) findViewById(R.id.gameView1);
		gameEditor = new GameEditor(gameView.getCells());
		
		//	instantiate thread
		thread = new Thread() {
			public void run() {
				gameView.setCells(gameEditor.getNextIteration());
				gameView.invalidate();
				mainHandler.postDelayed(this, 500);
				cntr--;
				if(cntr == 0)
					mainHandler.removeCallbacks(this);
			}
		};
		
		// set on click listeners
		runOnceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainHandler.removeCallbacks(thread);
				cntr = 1;
				mainHandler.postDelayed(thread, 0);
			}
		});
		
		
		runTwentyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainHandler.removeCallbacks(thread);
				cntr = 20;
				mainHandler.postDelayed(thread, 0);
			}
		});
		
			
		runButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainHandler.removeCallbacks(thread);
				cntr = -1;
				mainHandler.postDelayed(thread, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

}