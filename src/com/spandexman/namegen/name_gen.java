package com.spandexman.namegen;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.Random;

public class name_gen extends Activity {
    /** Called when the activity is first created. */

    TextView tvName, title;
    RadioButton chkMale, chkFemale;
    Button gen;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Typeface gooddog;
        gooddog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.otf");
        title = (TextView) findViewById(R.id.title);
        tvName = (TextView) findViewById(R.id.tv_name);
        title.setTypeface(gooddog);
        tvName.setTypeface(gooddog);
        chkMale = (RadioButton) findViewById(R.id.radio_male);
        chkFemale = (RadioButton) findViewById(R.id.radio_female);
        gen = (Button) findViewById(R.id.btn_gen);
        
        gen.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Resources res = getResources();
				String[] vowels = res.getStringArray(R.array.vowels);
				String[] suffixes = res.getStringArray(R.array.suffixes);
				String[] consonants = res.getStringArray(R.array.consonants);
				StringBuffer name = new StringBuffer();
				final Random rand = new Random();
				int conlen = 25; 
				int jlim = 18; //remove nonsense starting consonants
				int vowlen = 8; 
				int rv, rs, rc, rl;
								
				rl = 3+rand.nextInt(3);
				
				boolean VowelNow=true;
				if (rand.nextInt()%2 == 0) //50% chance to start with consonant
					VowelNow=false;
				else if (rl==5) //5 letter names don't usually start with vowels
					VowelNow = false;
				
				if (VowelNow)
					vowlen=5; //remove nonsense starting vowels
				
				for(int i=rl;i>0;i--)
				{
					if(VowelNow)
					{
						rv = rand.nextInt(vowlen);
						name.append(vowels[rv]);
						if (rv>5)
							vowlen=6; //only one double vowel in the name
						else
							vowlen=8;
						jlim = 25;
						conlen = 35;
					}
					else
					{
						rc = rand.nextInt(conlen);
						if (rc>jlim)
							name.append("j");
						else
							name.append(consonants[rc]);
						jlim = 25;
						conlen = 35;				
					}
					VowelNow = !VowelNow;				
				}
				name.append(" ");
				if ((rand.nextInt()%2)==0)
				{

					if(chkMale.isChecked())
					{
						rs = rand.nextInt(3);
						name.append(suffixes[rs]);
					}
					else
					{
						rs = 2 + rand.nextInt(2);
						name.append(suffixes[rs]);
					}
				}
				tvName.setText(name);
			}
		});
    }
}