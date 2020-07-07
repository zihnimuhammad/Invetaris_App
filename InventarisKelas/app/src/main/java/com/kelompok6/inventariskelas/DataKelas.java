package com.kelompok6.inventariskelas;

import java.util.ArrayList;

public class DataKelas {
    private static String[][] data = new String[][]{
            {"Ruang Embedded 001", "Gedung AA", "20", "https://lh3.googleusercontent.com/proxy/hqRIIbKMRsjAh6EYFb-uNcbAL7JDZmVK51Ur2j1VQL8HUMa3E3RFaDf10mxNxropIJIANz4PDCQjCp7WnTnuZzGkjhAlLCFpkmp6RVQ7_944Js6tSWkyn4U"},
            {"Ruang Embedded 002", "Gedung AA", "20", "https://www.its.ac.id/informatika/wp-content/uploads/sites/44/2018/08/MG_2052-1024x683.jpg"},
            {"Ruang Embedded 003", "Gedung AA", "20", "data:image/jpeg;base64,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"},
            {"Ruang AA 203", "Gedung AA", "26", "https://cintakasihtzuchi.sch.id/wp-content/uploads/2016/11/Ruang-Kelas-SD-1150x767.jpg"},
            {"Ruang AA 204", "Gedung AA", "26", "https://jabar.pojoksatu.id/wp-content/uploads/2017/01/ruang-kelas-baru-390x250.jpg"},
            {"Ruang AA 205", "Gedung AA", "26", "https://statik.tempo.co/data/2020/03/16/id_923565/923565_720.jpg"},
            {"Ruang AA 301", "Gedung AA", "30", "https://cintakasihtzuchi.sch.id/wp-content/uploads/2016/11/Ruang-Kelas-SMA-1150x767.jpg"},
            {"Ruang AA 302", "Gedung AA", "28", "https://moondoggiesmusic.com/wp-content/uploads/2019/05/TUJUAN-HIASAN-KELAS.jpg"},
            {"Ruang AA 303", "Gedung AA", "30", "https://www.qureta.com/uploads/post/39996_53681.jpg"},
            {"Ruang AA 304", "Gedung AA", "28", "https://media.suara.com/pictures/970x544/2019/08/23/26065-siswa-tidur-di-kelas-saat-guru-mengajar-instagramatlambe-turah.jpg"},
            {"Ruang AA 305", "Gedung AA", "30", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT2JDIIcK3BQ-A0Ox7DXiGstaOqGk8E5tcSVH9dg5lTAoTupmgI&usqp=CAU"},
            {"Ruang GSG 207", "Gedung GSG", "30", "https://img.okezone.com/content/2018/01/30/196/1852313/dekorasi-kelas-pelangi-bikin-anak-anak-semangat-belajar-di-sekolah-mZygdipT3D.jpg"},
            {"Ruang GSG 208", "Gedung GSG", "30", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSwC56NLFuv9UF9vjhQ2vDen38xVAR0ZsEf3eHo9iIkDWwbZgUF&usqp=CAU"},
    };

    static ArrayList<Kelas> getListData(){
        ArrayList<Kelas> list = new ArrayList<>();
        for (String[] aData : data){
            Kelas kelas = new Kelas();
            kelas.setNama_kelas(aData[0]);
            kelas.setLokasi(aData[1]);
            kelas.setKapasitas(aData[2]);
            kelas.setFoto_kelas(aData[3]);

            list.add(kelas);
        }

        return list;
    }
}
