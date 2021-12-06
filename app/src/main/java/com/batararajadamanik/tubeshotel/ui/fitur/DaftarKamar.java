package com.batararajadamanik.tubeshotel.ui.fitur;

import java.util.ArrayList;

public class DaftarKamar {
    public ArrayList<Kamar> KAMAR;

    public DaftarKamar(){
        KAMAR = new ArrayList();
        KAMAR.add(STANDARD);
        KAMAR.add(SUPERIOR);
        KAMAR.add(DELUXE);
        KAMAR.add(JUNIOR);
        KAMAR.add(SUITE);
    }

    public static final Kamar STANDARD = new Kamar("Standard Room","1 kasur queen size",350000,
            "https://images-ext-2.discordapp.net/external/PTe1T0neADnr4tO8-IjcTPVe7GvgEBY7BMt7yF5Ts50/https/upload.wikimedia.org/wikipedia/commons/thumb/5/56/Hotel-room-renaissance-columbus-ohio.jpg/1024px-Hotel-room-renaissance-columbus-ohio.jpg");

    public static final Kamar SUPERIOR = new Kamar("Superior Room","1 kasur king size",600000,
            "https://images-ext-1.discordapp.net/external/wOdSm1XJE7TMVJIU2Rc_yJBiashETRqe_wlNM5VGL5U/https/i.pinimg.com/originals/30/6a/f3/306af3bc7c6ab631736ee2a660f28a3f.jpg");

    public static final Kamar DELUXE = new Kamar("Deluxe Room","Kasur double bed",700000,
            "https://images-ext-2.discordapp.net/external/tPFSQ0FZizp0x9FuND6feVpPnJD6S4MQ4iteDcVsyK8/%3Fwidth%3D1000%26format%3Djpeg%26auto%3Dwebp/https/i.insider.com/584eacc5dd0895cb558b4868");
    public static final Kamar JUNIOR = new Kamar("Junior Suite","1 kasur king size, ruang tamu",1500000, "https://images-ext-2.discordapp.net/external/WJM7awOidF7wB0SLSAPSZSccxNHpRIZeVj4tiJR6_1Q/https/p.bookcdn.com/data/Photos/Big/10095/1009553/1009553992/Hotel-Ordinary-Bangkok-photos-Exterior-Hotel-Ordinary-Bangkok.JPEG");

    public static final Kamar SUITE = new Kamar("Suite Room","1 kasur king size, ruang tamu, dapur",2000000,
            "https://images-ext-2.discordapp.net/external/R-Du2dZP__9mCXIfjEW1qdsjsPrNxLJ70aTKqSgn0sc/https/ak-d.tripcdn.com/images/22050x000000l85ngA872_R_550_412_R5_D.jpg");



}
