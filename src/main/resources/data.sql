insert into Banka(banka3kod, banka4kod, swift_kod, naziv, port, lozinka, tip) values ('123', 'BANK', 'BANKRS22', 'Banka_1', '8081', 'pass', 'OBICNA');
insert into Banka(banka3kod, banka4kod, swift_kod, naziv, port, lozinka, tip) values ('124', 'BKKK', 'BKKKRS22', 'Banka_2', '8082', 'pass', 'OBICNA');
insert into Banka(banka3kod, banka4kod, swift_kod, naziv, port, lozinka, tip) values ('125', 'BAKK', 'BAKKRS22', 'Banka_3', '8083', 'pass', 'NARODNA');

insert into Firma(naziv, adresa, pib, port, lozinka) values ('Ceks', 'Ceksa Ceksovskog 9', '1234512345', '9091', 'pass');
insert into Firma(naziv, adresa, pib, port, lozinka) values ('Ceks1', 'Ceksa Ceksovskog 10', '5432154321', '9092', 'pass');

insert into Racun(broj_racuna, firma_id, banka_id, obracunski, valuta, novo_stanje, rezervisana_sredstva) values ('123123123123123123', null, 1, true, 'rsd', 50000, 0);
insert into Racun(broj_racuna, firma_id, banka_id, obracunski, valuta, novo_stanje, rezervisana_sredstva) values ('124124124124124124', null, 2, true, 'rsd', 40000, 0);
insert into Racun(broj_racuna, firma_id, banka_id, obracunski, valuta, novo_stanje, rezervisana_sredstva) values ('125125125125125125', null, 3, true, 'rsd', 0, 0);

insert into Racun(broj_racuna, firma_id, banka_id, obracunski, valuta, novo_stanje, rezervisana_sredstva) values ('123111111111111111', 1, 1, false, 'rsd', 50000, 0);
insert into Racun(broj_racuna, firma_id, banka_id, obracunski, valuta, novo_stanje, rezervisana_sredstva) values ('124222222222222222', 2, 2, false, 'rsd', 50000, 0);
