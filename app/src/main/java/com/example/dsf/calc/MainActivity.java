package com.example.dsf.calc;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView Scr;
    private double Bufer;
    private boolean Buferempty;
    private boolean Saved;
    private boolean FlagMemory;
    private double Memory;
    private Character Operation;
    private ButtonNumberClickListener btnNumberClick;
    private ButtonOperationsClickListener btnOperationsClick;
    private ButtonControlClickListener btnControlClick;
    private ButtonFunctionClickListener btnFunctionClick;
    private ButtonMemoryClickListener btnMemoryClick;
    private double ValuePower;
    private boolean FlagPower;
    int idListNumber[]={R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btnPoint,R.id.btnPM};
    int idListOperations[]={R.id.btnDiv,R.id.btnMult,R.id.btnAdd,R.id.btnSub};
    int idListFunctions[]={R.id.btnAbs,R.id.btnCos,R.id.btnSin,R.id.btnTan,R.id.btnS,R.id.btnPrs,R.id.btnP};
    int idListMemory[]={R.id.btnMC,R.id.btnMR,R.id.btnMM,R.id.btnMP};
    int idListControl[]={R.id.btnBck,R.id.btnC,R.id.btnCE,R.id.btnRezult};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FlagPower=false;
        ValuePower=0;
        Buferempty=true;
        FlagMemory=false;
        Memory=0;
        Saved=false;
        Bufer=0;
        Scr=(TextView) findViewById(R.id.textView);
        Scr.setText("0");
        btnNumberClick = new ButtonNumberClickListener();
        btnControlClick = new ButtonControlClickListener();
        btnOperationsClick = new ButtonOperationsClickListener();
        btnFunctionClick = new ButtonFunctionClickListener();
        btnMemoryClick = new ButtonMemoryClickListener();
        for(int id:idListNumber){
          View v = (View) findViewById(id);
         v.setOnClickListener(btnNumberClick);
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            for(int id:idListFunctions){
                View v = (View) findViewById(id);
                v.setOnClickListener(btnFunctionClick);
            }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            for(int id:idListMemory){
            View v = (View) findViewById(id);
            v.setOnClickListener(btnMemoryClick);
          }

        for(int id:idListOperations){
            View v = (View) findViewById(id);
            v.setOnClickListener(btnOperationsClick);
        }


        for(int id:idListControl){
            View v= (View) findViewById(id);
            v.setOnClickListener(btnControlClick);
        }
    }

    private class ButtonNumberClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(Saved||Scr.getText()=="NaN"||Scr.getText()=="Infinity"){
                Scr.setText("0");
                Saved=false;
            }
            switch (v.getId()) {
                case R.id.btn0:{
                    if(Scr.getText()!="0") Scr.setText(Scr.getText() + "0");
                    break;
                }
                case R.id.btn1:{
                    if(Scr.getText()=="0") Scr.setText("1"); else Scr.setText(Scr.getText() + "1");
                    break;
                }
                case R.id.btn2:{
                    if(Scr.getText()=="0") Scr.setText("2"); else Scr.setText(Scr.getText() + "2");
                    break;
                }
                case R.id.btn3:{
                    if(Scr.getText()=="0") Scr.setText("3"); else Scr.setText(Scr.getText() + "3");
                    break;
                }
                case R.id.btn4:{
                    if(Scr.getText()=="0") Scr.setText("4"); else Scr.setText(Scr.getText() + "4");
                    break;
                }
                case R.id.btn5:{
                    if(Scr.getText()=="0") Scr.setText("5"); else Scr.setText(Scr.getText() + "5");
                    break;
                }
                case R.id.btn6:{
                    if(Scr.getText()=="0") Scr.setText("6"); else Scr.setText(Scr.getText() + "6");
                    break;
                }
                case R.id.btn7:{
                    if(Scr.getText()=="0") Scr.setText("7"); else Scr.setText(Scr.getText() + "7");
                    break;
                }
                case R.id.btn8:{
                    if(Scr.getText()=="0") Scr.setText("8"); else Scr.setText(Scr.getText() + "8");
                    break;
                }
                case R.id.btn9:{
                    if(Scr.getText()=="0") Scr.setText("9"); else Scr.setText(Scr.getText() + "9");
                    break;
                }
                case R.id.btnPoint:{
//                    for(int i=0;i<Scr.getText().length();i++){
//                        if(Scr.getText().charAt(i)=='.') break;
//                    }

                    if (Scr.getText().toString().indexOf(".")==-1) Scr.setText(Scr.getText() + ".");
                    break;
                }
                case R.id.btnPM:{
                        double tmp=Double.valueOf(Scr.getText().toString());
                            if(tmp!=0) Scr.setText(String.valueOf(tmp*(-1)));
                    break;
                }
            }
        }
    }

    private class ButtonOperationsClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String s = Scr.getText().toString();
            double val = Double.valueOf(s);
            if (!Buferempty) {
                switch (Operation) {
                    case '+': {
                        Bufer = Bufer + val;
                        break;
                    }
                    case '-': {
                        Bufer = Bufer - val;
                        break;
                    }
                    case '*': {
                        Bufer = Bufer * val;
                        break;
                    }
                    case '/': {
                        Bufer = Bufer / val;
                        break;
                    }
                    default: {
                        break;
                    }

                }
                Saved=true;
                Scr.setText(String.valueOf(Bufer));
            } else Bufer = val;
            switch (v.getId()) {
                case R.id.btnAdd: {
                    Operation = '+';
                    break;
                }
                case R.id.btnDiv: {
                    Operation = '/';
                    break;
                }
                case R.id.btnSub: {
                    Operation = '-';
                    break;
                }
                case R.id.btnMult: {
                    Operation = '*';
                    break;
                }
                default:{
                    break;
                }
            }
            Buferempty=false;
            Saved = true;
        }
    }

    private class ButtonControlClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnRezult: {
                    String s = Scr.getText().toString();
                    double val = Double.valueOf(s);
                    if (!Buferempty) {
                        switch (Operation) {
                            case '+': {
                                Bufer = Bufer + val;
                                break;
                            }
                            case '-': {
                                Bufer = Bufer - val;
                                break;
                            }
                            case '*': {
                                Bufer = Bufer * val;
                                break;
                            }
                            case '/': {
                                Bufer = Bufer / val;
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else Bufer = val;
                    Buferempty=true;
                    Saved=true;
                    Scr.setText(String.valueOf(Bufer));
                    break;
                }
                case R.id.btnC: {
                    Buferempty=true;
                    Saved=false;
                    Scr.clearComposingText();
                    Scr.setText("0");
                        for(int id:idListOperations){
                        View elem = (View)findViewById(id);
                        elem.setEnabled(true);
                        }
                    View tmp;
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){ tmp=(View) findViewById(R.id.btnPrs);
                    tmp.setEnabled(true);}
                    tmp=(View) findViewById(R.id.btnRezult);
                    tmp.setEnabled(true);
                    FlagPower=false;
                    break;
                }
                case R.id.btnCE: {
                    Scr.setText("0");
                    break;
                }
                case R.id.btnBck:{
                    if(Saved) {
                        Saved = false;
                        Scr.setText("0");
                    } else
                        if (Scr.getText().toString().length() > 1) {
                            String s = Scr.getText().toString();
                            s = s.substring(0, s.length() - 1);
                            Scr.setText(s);
                        } else Scr.setText("0");

                    break;
                }
            }
        }
    }

    private class ButtonFunctionClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnSin: {
                    double tmp = Double.valueOf(Scr.getText().toString());
                    Saved = true;
                    Scr.setText(String.valueOf(Math.sin(tmp)));
                    break;
                }
                case R.id.btnCos:{
                    double tmp = Double.valueOf(Scr.getText().toString());
                    Saved = true;
                    Scr.setText(String.valueOf(Math.cos(tmp)));
                    break;
                }
                case R.id.btnTan:{
                    double tmp = Double.valueOf(Scr.getText().toString());
                    Saved = true;
                    Scr.setText(String.valueOf(Math.tan(tmp)));
                    break;
                }
                case R.id.btnAbs:{
                    double tmp = Double.valueOf(Scr.getText().toString());
                    Saved = true;
                    Scr.setText(String.valueOf(Math.abs(tmp)));
                    break;
                }
                case R.id.btnS:{
                    double tmp = Double.valueOf(Scr.getText().toString());
                    Saved = true;
                    Scr.setText(String.valueOf(Math.sqrt(tmp)));
                    break;
                }
                case R.id.btnPrs:{
                    Saved=true;
                    Scr.setText(String.valueOf(Bufer/100*Double.valueOf(Scr.getText().toString())));
                    break;
                }
                case R.id.btn1ofX:{
                    Saved=true;
                    Scr.setText(String.valueOf(1/Double.valueOf(Scr.getText().toString())));
                    break;
                }
                case R.id.btnP:{
                    if(FlagPower){
                        Saved=true;
                        FlagPower=false;
                        Scr.setText(String.valueOf(Math.pow(ValuePower,Double.valueOf(Scr.getText().toString()))));
                        for(int id:idListOperations){
                            View elem = (View)findViewById(id);
                            elem.setEnabled(true);
                        }
                        View tmp=(View) findViewById(R.id.btnPrs);
                        tmp.setEnabled(true);
                        tmp=(View) findViewById(R.id.btnRezult);
                        tmp.setEnabled(true);
                    } else {
                        Saved=true;
                        FlagPower=true;
                        ValuePower=Double.valueOf(Scr.getText().toString());
                        for(int id:idListOperations){
                            View elem = (View)findViewById(id);
                            elem.setEnabled(false);
                        }
                        View tmp=(View) findViewById(R.id.btnPrs);
                        tmp.setEnabled(false);
                        tmp=(View) findViewById(R.id.btnRezult);
                        tmp.setEnabled(false);
                    }
                    break;
                }
            }
        }
    }
    private class ButtonMemoryClickListener implements View.OnClickListener{
        @Override
                public void onClick(View v){
                    switch (v.getId()){
                        case R.id.btnMC: {
                            Memory=0;
                            FlagMemory=false;
                            break;
                        }
                        case R.id.btnMR:
                        {
                            if(FlagMemory){
                                Scr.setText(String.valueOf(Memory));
                                Saved=true;
                            }
                            break;
                        }
                        case R.id.btnMP : {
                            if(!FlagMemory) FlagMemory=true;
                            Memory=Memory+Double.valueOf(Scr.getText().toString());
                            Saved=true;
                            break;
                        }
                        case R.id.btnMM:{
                            if(!FlagMemory) FlagMemory=true;
                            Memory=Memory -Double.valueOf(Scr.getText().toString());
                            Saved=true;
                            break;
                        }
                    }
                }
    }
}