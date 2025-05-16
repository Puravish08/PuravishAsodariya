package com.portfolio.puravishasodariya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.puravishasodariya.R
import com.portfolio.puravishasodariya.models.Certification

class CertificationsAdapter(
    private val certifications: List<Certification>,
    private val onCertificateClick: (Certification) -> Unit
) : RecyclerView.Adapter<CertificationsAdapter.CertificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.certification_item_layout, parent, false)
        return CertificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        holder.bind(certifications[position])
    }

    override fun getItemCount() = certifications.size

    inner class CertificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val certificationLogo: ImageView = itemView.findViewById(R.id.certificationLogo)
        private val certificationName: TextView = itemView.findViewById(R.id.certificationName)
        private val issuingOrganization: TextView = itemView.findViewById(R.id.issuingOrganization)
        private val issueDate: TextView = itemView.findViewById(R.id.issueDate)
        private val btnViewCertificate: Button = itemView.findViewById(R.id.btnViewCertificate)

        fun bind(certification: Certification) {
            certificationLogo.setImageResource(certification.logoResId)
            certificationName.text = certification.name
            issuingOrganization.text = certification.issuingOrganization
            issueDate.text = certification.issueDate

            btnViewCertificate.setOnClickListener {
                onCertificateClick(certification)
            }
        }
    }
}
