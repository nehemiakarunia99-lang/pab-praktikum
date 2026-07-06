package com.nehem.pabweek3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nehem.pabweek3.databinding.ActivityMainBinding

/**
 * Halaman Utama
 * Tugas Praktikum PAB (Week 3)
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Data diri mahasiswa
    private val nim = "L0124114"
    private val nama = "Nehemia Karunia Dewa Ndaru"
    private val jurusan = "Informatika"
    private val angkatan = "24"
    private val deskripsi = "Saya adalah Mahasiswa Informatika semester 4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToProfile.setOnClickListener {
            goToProfile()
        }
    }

    // EXPLICIT INTENT: berpindah ke ProfileActivity sambil mengirim data
    private fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(EXTRA_NIM, nim)
            putExtra(EXTRA_NAMA, nama)
            putExtra(EXTRA_JURUSAN, jurusan)
            putExtra(EXTRA_ANGKATAN, angkatan)
            putExtra(EXTRA_DESKRIPSI, deskripsi)
        }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_NIM = "extra_nim"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_JURUSAN = "extra_jurusan"
        const val EXTRA_ANGKATAN = "extra_angkatan"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
    }
}
