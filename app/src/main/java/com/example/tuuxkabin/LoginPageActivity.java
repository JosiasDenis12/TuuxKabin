package com.example.tuuxkabin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_with_image);

        // Configurar el botón de iniciar sesión
        MaterialButton loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los campos
                String email = ((TextView) findViewById(R.id.emailInput)).getText().toString();
                String password = ((TextView) findViewById(R.id.passwordInput)).getText().toString();

                // Validar los campos (ejemplo muy básico)
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginPageActivity.this,
                            "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // En un caso real, aquí se haría la validación contra un servidor
                    navigateToHomeScreen();
                }
            }
        });

        // Configurar el botón de Google
        MaterialButton googleLoginButton = findViewById(R.id.googleLoginButton);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // En un caso real, aquí se implementaría la autenticación con Google
                Toast.makeText(LoginPageActivity.this,
                        "Iniciando sesión con Google...", Toast.LENGTH_SHORT).show();
                // Simulación: esperamos un momento y navegamos
                googleLoginButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigateToHomeScreen();
                    }
                }, 1500);
            }
        });

        // Configurar el botón de Facebook
        MaterialButton facebookLoginButton = findViewById(R.id.facebookLoginButton);
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // En un caso real, aquí se implementaría la autenticación con Facebook
                Toast.makeText(LoginPageActivity.this,
                        "Iniciando sesión con Facebook...", Toast.LENGTH_SHORT).show();
                // Simulación: esperamos un momento y navegamos
                facebookLoginButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigateToHomeScreen();
                    }
                }, 1500);
            }
        });

        // Configurar el botón de acceso como invitado
        MaterialButton guestLoginButton = findViewById(R.id.guestLoginButton);
        guestLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar directamente a la pantalla principal como invitado
                Toast.makeText(LoginPageActivity.this,
                        "Accediendo como invitado...", Toast.LENGTH_SHORT).show();
                navigateToHomeScreen();
            }
        });

        // Configurar el botón de registro
        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la pantalla de registro
                Toast.makeText(LoginPageActivity.this,
                        "Función de registro en desarrollo", Toast.LENGTH_SHORT).show();
                // En un caso real, aquí navegaríamos a RegisterActivity
                // Intent intent = new Intent(LoginPageActivity.this, RegisterActivity.class);
                // startActivity(intent);
            }
        });

        // Configurar el botón de olvidé mi contraseña
        TextView forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la pantalla de recuperación de contraseña
                Toast.makeText(LoginPageActivity.this,
                        "Función de recuperación de contraseña en desarrollo", Toast.LENGTH_SHORT).show();
                // En un caso real, aquí navegaríamos a ForgotPasswordActivity
                // Intent intent = new Intent(LoginPageActivity.this, ForgotPasswordActivity.class);
                // startActivity(intent);
            }
        });
    }

    private void navigateToHomeScreen() {
        // Asumiendo que HomeActivity es la pantalla principal de la app después del login
        Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finaliza esta actividad para que no quede en el stack
    }
}