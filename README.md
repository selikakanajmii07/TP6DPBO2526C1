# TP6DPBO2526C1
Saya Selika Kanajmi dengan NIM 2408495 mengerjakan TP 6 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya
maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan

PENJELASAN
Program terdiri dari beberapa class yang memiliki fungsi berbeda yaitu:
App.java
Menjalankan aplikasi dan mengatur perpindahan halaman menggunakan CardLayout.
MainMenu.java
Menampilkan menu utama dengan tombol Play dan Exit.
Logic.java
Mengatur logika permainan, gravitasi, pergerakan pipa, tabrakan, skor, dan restart.
View.java
Menampilkan background, burung, pipa, skor, dan Game Over.
Player.java
Menyimpan data burung, seperti posisi, ukuran, dan kecepatan.
Pipe.java
Menyimpan data objek pipa sebagai rintangan.
AudioManager.java
Mengatur efek suara selama permainan.

Ada 6 fitur di program ini yaitu:
1. Main Menu
   Menampilkan halaman awal permainan yang memiliki tombol Play untuk memulai permainan dan Exit untuk keluar dari aplikasi.
2. Kontrol Burung
   Pemain dapat mengendalikan gerakan burung menggunakan tombol Space, ketika ditekan burung akan terbang ke atas sedangkan ketika tidak ditekan burung akan turun karena gravitasi.
3. Rintangan Pipa
   Pipa akan muncul dari sisi kanan layar dan bergerak ke kiri, pemain harus melewati celah di antara pipa agar tidak menabrak.
4. Sistem Skor
   Skor bertambah satu poin setiap kali burung berhasil melewati pips, jumlah skor ditampilkan pada bagian atas layar.
5. Game Over dan Restart
   Permainan berakhir ketika burung menabrak pipa atau jatuh melewati batas bawah layar, pemain dapat menekan tombol R untuk mengulang permainan dari awal.
6. Efek Suara
   Permainan memiliki efek suara ketika burung terbang, mendapatkan skor, menabrak rintangan, dan mengalami Game Over.

Alur program
1. Program dijalankan melalui App.java
2. Sistem menampilkan halaman Main Menu
3. Pemain menekan tombol Play untuk memulai permainan dan bisa menekan tombol exit jika ingin keluar dari aplikasi
4. Setelah permainan dimulai burung akan bergerak
5. Pemain menekan tombol Space untuk membuat burung terbang ke atas.
6. Pipa akan muncul dan bergerak dari kanan ke kiri.
7. Pemain harus menghindari pipa agar burung tidak mengalami tabrakan.
8. Ketika burung berhasil melewati sepasang pipa skor akan bertambah satu poin. Jika burung menabrak pipa atau jatuh melewati batas bawah layar, permainan akan berakhir.
9. Sistem menampilkan tulisan Game Over dan pemain dapat menekan tombol R untuk memulai ulang permainan.
