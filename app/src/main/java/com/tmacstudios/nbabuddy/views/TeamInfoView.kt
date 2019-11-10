package com.tmacstudios.nbabuddy.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tmacstudios.nbabuddy.R
import com.tmacstudios.nbabuddy.models.Team
import com.tmacstudios.nbabuddy.utils.teamNames

class TeamInfoView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private lateinit var teamImage: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamRecord: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.team_info, this, true)

        teamImage = findViewById(R.id.team_image)
        teamName = findViewById(R.id.team_name)
        teamRecord = findViewById(R.id.team_record)
    }

    fun setTeam(team: Team) {
        val resources = context.resources
        val packageName = context.packageName
        val imageId = resources.getIdentifier(team.triCode.toLowerCase(), "drawable", packageName)
        teamImage.setImageDrawable(ContextCompat.getDrawable(context, imageId))
        teamName.text = teamNames[team.triCode]
        teamRecord.text = String.format("(%s-%s)", team.win, team.loss)
    }
}