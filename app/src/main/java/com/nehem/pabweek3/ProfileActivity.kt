package com.nehem.pabweek3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nehem.pabweek3.databinding.ActivityProfileBinding

/**
 * Halaman Profile
 * Tugas Praktikum PAB (Week 3)
 *
 * Menerima data dari MainActivity (Explicit Intent),
 * lalu membagikan data tersebut ke aplikasi lain (Implicit Intent - ACTION_SEND)
 * saat tombol Share ditekan.
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data yang dikirim dari MainActivity
        val nim = intent.getStringExtra(MainActivity.EXTRA_NIM) ?: "-"
        val nama = intent.getStringExtra(MainActivity.EXTRA_NAMA) ?: "-"
        val jurusan = intent.getStringExtra(MainActivity.EXTRA_JURUSAN) ?: "-"
        val angkatan = intent.getStringExtra(MainActivity.EXTRA_ANGKATAN) ?: "-"
        val deskripsi = intent.getStringExtra(MainActivity.EXTRA_DESKRIPSI) ?: "-"

        binding.tvNim.text = nim
        binding.tvNama.text = nama
        binding.tvJurusanAngkatan.text = "$jurusan, Angkatan $angkatan"
        binding.tvDeskripsi.text = deskripsi

        binding.btnShare.setOnClickListener {
            shareProfile(nim, nama, jurusan, angkatan, deskripsi)
        }
    }

    // IMPLICIT INTENT: membagikan data profil ke aplikasi lain (WhatsApp, Email, dll)
    private fun shareProfile(
        nim: String,
        nama: String,
        jurusan: String,
        angkatan: String,
        deskripsi: String
    ) {
        val shareText = """
            Profil Mahasiswa
            NIM: $nim
            Nama: $nama
            Jurusan: $jurusan
            Angkatan: $angkatan
            Deskripsi: $deskripsi
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, "Bagikan profil via"))
    }
}
