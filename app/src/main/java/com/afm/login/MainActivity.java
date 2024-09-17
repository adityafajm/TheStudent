package com.afm.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.app.DatePickerDialog;

public class MainActivity extends AppCompatActivity {

    EditText dob, name, email, username, id, password, address, phone;
    Spinner genderSpinner;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi elemen-elemen input
        name = findViewById(R.id.edt_name);
        email = findViewById(R.id.edt_email);
        username = findViewById(R.id.edt_username);
        id = findViewById(R.id.edt_id);
        password = findViewById(R.id.edt_password);
        dob = findViewById(R.id.edt_date);
        address = findViewById(R.id.edt_alamat);
        phone = findViewById(R.id.edt_nohp);
        genderSpinner = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.submit_button);

        // Setup DatePickerDialog untuk tanggal lahir
        dob.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dob.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Set Adapter untuk Spinner jenis kelamin
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // Set onClickListener untuk tombol submit
        submitButton.setOnClickListener(v -> {
            // Ambil data dari input pengguna
            String nameText = name.getText().toString();
            String emailText = email.getText().toString();
            String usernameText = username.getText().toString();
            String idText = id.getText().toString();
            String passwordText = password.getText().toString();
            String dobText = dob.getText().toString();
            String addressText = address.getText().toString();
            String phoneText = phone.getText().toString();
            String genderText = genderSpinner.getSelectedItem().toString();

            // Periksa jika ada data yang kosong
            if (nameText.isEmpty() || emailText.isEmpty() || usernameText.isEmpty() ||
                    idText.isEmpty() || passwordText.isEmpty() || dobText.isEmpty() ||
                    addressText.isEmpty() || phoneText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Mohon lengkapi semua data!", Toast.LENGTH_LONG).show();
            } else {
                // Tampilkan notifikasi dengan data yang diisi
                String notificationMessage = "Nama: " + nameText + "\nEmail: " + emailText +
                        "\nUsername: " + usernameText + "\nID: " + idText +
                        "\nPassword: " + passwordText + "\nJenis Kelamin: " + genderText +
                        "\nTanggal Lahir: " + dobText + "\nAlamat: " + addressText +
                        "\nNo. Telp: " + phoneText;

                Toast.makeText(MainActivity.this, notificationMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}