
package com.example.questtugasuserinput_0136

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.questtugasuserinput_0136.ui.theme.QuestTugasUserInput_0136Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            QuestTugasUserInput_0136Theme {
                FormBiodata()
            }
        }
    }
}
