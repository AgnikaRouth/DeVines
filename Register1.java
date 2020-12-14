package devines.com.devines20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Register1 extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =  //custom define pattern for password
            Pattern.compile("^" +
                    "(?=.*[0-9])" +           //at least 1 number
                    //"(?=.*[a-z A-Z])" +      //any letter
                    //"(?=.*[a-z])" +             //atleast 1 lower case letter
                    //"(?=.*[A-Z])" +             //atleast 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    public static final String TAG = "Registration";
    EditText uemail;
    EditText uphone;
    EditText upass1, upass2;
    EditText uname, usurname;
    String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        //taking Firebase authentication instance
        mAuth = FirebaseAuth.getInstance();
        //taking Firebase Firestore instance
        fStore = FirebaseFirestore.getInstance();

        //initialising all views from above objects
        uemail =(EditText) findViewById(R.id.etemail);
        usurname = (EditText) findViewById(R.id.etsurname);
        uname =(EditText) findViewById(R.id.etname);
        uphone =(EditText) findViewById(R.id.etphone);
        upass1 = (EditText) findViewById(R.id.etpass1);
        upass2 = (EditText) findViewById(R.id.etpass2);
        next = (Button) findViewById(R.id.btnext1);

        //Set on Click listener for Registration Button

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                {
                    registerNewUser();
                    startActivity(new Intent(Register1.this, Register2.class));
                }

                //or directly call the function registerNewUser()
            }
        });

    }

    private boolean validateEmail() {

        String emailInput = uemail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (emailInput.isEmpty()) {
            uemail.setError("Le champ ne peut pas être vide\"");
            return false;
        }
        //else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        else if(!emailInput.matches(emailPattern))
        {
            uemail.setError("Veuillez mettez une adresse email valide");
            return false;
        } else {
            uemail.setError(null);
            return true;
        }
    }

    //validate phone number
    private boolean validatePhoneNumber() {
        String phoneNumber = uphone.getText().toString().trim();
        if (phoneNumber.isEmpty()) {
            uphone.setError("Le champ ne peut pas être vide");
            return false;
        } else if (phoneNumber.length() > 12) {
            uphone.setError("Numéro de téléphone est trop long");
            return false;
        } else {
            uphone.setError(null);
            return true;
        }
    }

    //validate password

    private boolean validatePassword() {
        String passwordInput1 = upass1.getText().toString().trim();
        String passwordInput2 = upass2.getText().toString().trim();
        if ((passwordInput1.isEmpty()) | passwordInput2.isEmpty())
        {
            upass1.setError("Le champ ne peut pas être vide");
            upass2.setError("Le champ ne peut pas être vide");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput1).matches())
        {
            upass1.setError("Mot de passe est trop faible");
            return false;
        }
        else if(!(passwordInput2.equals(passwordInput1)))
        {
            upass2.setError("Veuillez saisir le même mot de passe");
            return false;
        }
        else {
            upass1.setError(null);
            return true;
        }
    }

    //validate all inputs are filled in
    private Boolean validate()
    {
        Boolean result = false;
        String emailInput = uemail.getText().toString().trim();
        String passwordInput1 = upass1.getText().toString().trim();
        String name = uname.getText().toString().trim();
        String surname = usurname.getText().toString().trim();
        String phone = uphone.getText().toString().trim();



        if(emailInput.isEmpty() || passwordInput1.isEmpty() || phone.isEmpty() || name.isEmpty() || surname.isEmpty() )
        {
            Toast.makeText(this, "Veuillez saisir tous les détails", Toast.LENGTH_SHORT).show();

        }
        else
        {
            if (!validateEmail() | !validatePhoneNumber() | !validatePassword()) {
                result = true;
            }
        }
        return  result;

    }


    private void registerNewUser() {
        //Take value of two user inputs in Strings
        final String email, password;
        email = uemail.getText().toString().trim();
        password = upass1.getText().toString().trim();
        final String phone = uphone.getText().toString().trim();
        final String name = uname.getText().toString().trim();

        //Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }


        //Create or register new user
        mAuth
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register1.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();
                            userID = mAuth.getCurrentUser().getUid(); //get userID of currently logged user
                            sendEmailVerification();
                            /*//Firebase Store
                            //Create collection and document
                            DocumentReference documentReference = fStore.collection("users")
                                    .document(userID);
                            //create data
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name", name); //the key names will act as attribute names in the database
                            user.put("PhoneNumber", phone);
                            user.put("Email", email);

                            //insert into the database
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess : user profile is created for" + userID);

                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure : error saing data for" + userID);
                                        }
                                    });

                            //after registration process is complete, go back to the main page
                            Intent intent
                                    = new Intent(Registration.this,
                                    MainActivity.class);
                            startActivity(intent);
                            //sendEmailVerification();*/
                        }
                        else
                        {
                            //Registration failed

                            //Toast.makeText( getApplicationContext(),"Registration failed!!"  + " Please try again later",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

        //send verification email in your mailbox

    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = mAuth .getCurrentUser();
        if(firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register1.this,"Enregistrement réussi, un courrier de vérification a été envoyé", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(Register1.this, Register2.class));
                    }
                    else
                    {
                        Toast.makeText(Register1.this,"Erreur d'envoyer l'email de vérification", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }





}