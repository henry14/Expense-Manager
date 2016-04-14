package org.grameenfoundation.expensemanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.grameenfoundation.expensemanager.Helpers.ExpenseProvider;
import org.grameenfoundation.expensemanager.Models.Expense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddExpense extends AppCompatActivity {//implements View.OnClickListener {

    private EditText itemEditText, costEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        itemEditText = (EditText)findViewById(R.id.itemEditText);
        costEditText = (EditText)findViewById(R.id.costEditText);

        Button saveBtn = (Button)findViewById(R.id.add_button);
        //saveBtn.setClickable(true);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mItem = itemEditText.getText().toString();
                Integer mCost = Integer.parseInt(costEditText.getText().toString());

                Calendar mCalendar = Calendar.getInstance();
                SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy");
                String mDate = mFormat.format(mCalendar.getTime());
                saveData(mItem, mCost, mDate);

                startActivity(new Intent(AddExpense.this, DailyActivity.class));


            }
        });
    }

    private void saveData(String item, Integer cost, String date) {

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Expense.LABEL, item);
        mContentValues.put(Expense.COST, cost);
        mContentValues.put(Expense.DATE, date);

        Uri uri = getContentResolver().insert(ExpenseProvider.mContentUri, mContentValues);

        //return new Expense(item, cost, date);

//        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

    }

    private List<Expense> getAllExpenses() {


        return null;
    }

    private List<Expense> getRangeOfExpenses(Date froDate, Date toDate){

        return null;
    }

}
