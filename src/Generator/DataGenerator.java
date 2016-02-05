package Generator;


import DBKit.SQLQuery;
import Entities.*;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.postgresql.util.PGmoney;

import java.util.*;

/**
 * Created by tomasz on 03.02.16.
 */
public class DataGenerator {

    public static enum Mode {
        ALPHA, ALPHANUMERIC, NUMERIC
    }



    public DataGenerator()
    {

    }

    public void generateDatabase()
    {
        //Generowanie insertow do stworzenia bazy danych
        List<String> inserts = new ArrayList<>();

        Map<String, String> kv = new HashMap<>();


        //Agenci
        List<Agent> agents = generateAgent(10000, 0);
        for(Agent a: agents)
        {
            kv.put("imie", a.getImie());
            kv.put("nazwisko", a.getNazwisko());
            kv.put("pesel", a.getPesel());
            kv.put("id", Integer.toString(a.getId()));
            kv.put("nr_agenta", Integer.toString(a.getNumerAgenta()));
            inserts.add(SQLQuery.insertSQL("Agent", kv));
        }
        kv.clear();
        List<Deweloper> dewelopers = generateDeweloper(10000, 0);
        for(Deweloper d: dewelopers)
        {
            kv.put("id", Integer.toString(d.getId()));
            kv.put("nazwa", d.getNazwa());
            kv.put("ulica", d.getUlica());
            kv.put("nr_domu", d.getNrDomu());
            kv.put("nr_mieszkania", d.getNrMieszkania());
            kv.put("miasto", d.getMiasto());
            inserts.add(SQLQuery.insertSQL("Deweloper", kv));
        }
        kv.clear();
        List<Adres> adres = generateAdres(10000, 0);
        for(Adres a: adres)
        {
            kv.put("id", Integer.toString(a.getId()));
            kv.put("kod_pocztowy", a.getKodPocztowy());
            kv.put("ulica", a.getUlica());
            kv.put("nr_domu", a.getNrDomu());
            kv.put("nr_mieszkania", a.getNrMieszkania());
            kv.put("miasto", a.getMiasto());
            inserts.add(SQLQuery.insertSQL("Adres", kv));
        }
        kv.clear();
        List<Miasto> miasto= generateMiasto(200, 0);
        for(Miasto m: miasto)
        {
            kv.put("id", Integer.toString(m.getId()));
            kv.put("nazwa", m.getNazwa());
            inserts.add(SQLQuery.insertSQL("Miasto", kv));
        }
        kv.clear();
        List<TypOferty> oferty = generateTypOferty(10, 0);
        for(TypOferty t: oferty)
        {
            kv.put("id", Integer.toString(t.getId()));
            kv.put("nazwa", t.getNazwa());
            inserts.add(SQLQuery.insertSQL("Typ_oferty", kv));
        }
        kv.clear();
        List<TypZdarzenia> zdarzenia = generateTypZdarzenia(10, 0);
        for(TypZdarzenia t: zdarzenia)
        {
            kv.put("id", Integer.toString(t.getId()));
            kv.put("nazwa", t.getNazwa());
            inserts.add(SQLQuery.insertSQL("Typ_zdarzenia", kv));
        }
        kv.clear();
        List<Cecha> cecha = generateCecha(100, 0);
        for(Cecha c: cecha)
        {
            kv.put("id", Integer.toString(c.getId()));
            kv.put("nazwa", c.getNazwa());
            kv.put("typ", Integer.toString(c.getTyp()));
            inserts.add(SQLQuery.insertSQL("Cecha", kv));
        }
        kv.clear();
        List<CechaOsiedla> cechaOsiedla = generateCechaOsiedla(100, 0);
        for(CechaOsiedla c: cechaOsiedla)
        {
            kv.put("id", Integer.toString(c.getId()));
            kv.put("nazwa", c.getNazwa());

            inserts.add(SQLQuery.insertSQL("Cecha_osiedla", kv));
        }
        kv.clear();
        List<CechaDzielnicy> cechaDzielnicy = generateCechaDzielnicy(100, 0);
        for(CechaDzielnicy c: cechaDzielnicy)
        {
            kv.put("id", Integer.toString(c.getId()));
            kv.put("nazwa", c.getNazwa());

            inserts.add(SQLQuery.insertSQL("Cecha_dzielnicy", kv));
        }
        kv.clear();
        List<Klient> klients = generateKlient(10000, 0, adres.size());
        for(Klient k: klients)
        {
            kv.put("id", Integer.toString(k.getId()));
            kv.put("imie", k.getImie());
            kv.put("nazwisko", k.getNazwisko());
            kv.put("plec", k.getPlec());
            kv.put("obywatelstwo", k.getObywatelstwo());
            kv.put("adres_zamieszkania", Integer.toString(k.getAdresZamieszkania()));
            kv.put("nr_telefonu", k.getNrTelefonu());
            kv.put("email", k.getEmail());
            inserts.add(SQLQuery.insertSQL("Klient", kv));
        }
        kv.clear();
        List<Preferencja> preferencja = generatePreferencja(1000, 0, cecha.size());
        for(Preferencja p: preferencja)
        {
            kv.put("id", Integer.toString(p.getId()));
            kv.put("wartosc", p.getWartosc());
            kv.put("od", Integer.toString(p.getOd()));
            kv.put("do", Integer.toString(p.getDo()));
            kv.put("cecha_id", Integer.toString(p.getCechaId()));
            inserts.add(SQLQuery.insertSQL("Preferencja", kv));
        }
        kv.clear();
        List<KlientPreferencje> klientPreferencje = generateKlientPreferencje(1000, 0, preferencja.size(), klients.size());
        for(KlientPreferencje k: klientPreferencje)
        {
            kv.put("klient_id", Integer.toString(k.getKlientId()));
            kv.put("preferencja", Integer.toString(k.getPreferencjaId()));
            inserts.add(SQLQuery.insertSQL("Klient_preferencja", kv));
        }
        kv.clear();
        List<Dzielnica> dzielnica = generateDzielnica(300, 0, miasto.size());
        for(Dzielnica d: dzielnica)
        {
            kv.put("id", Integer.toString(d.getId()));
            kv.put("miasto_id", Integer.toString(d.getId()));
            kv.put("nazwa", d.getNazwa());
            inserts.add(SQLQuery.insertSQL("Dzielnica", kv));
        }
        kv.clear();
        List<DzielnicaCechaDzielnicy> dzielnicaCechaDzielniciy = generateDzielnicaCechaDzielnicy(1000, 0, dzielnica.size(), cechaDzielnicy.size());
        for(DzielnicaCechaDzielnicy d: dzielnicaCechaDzielniciy)
        {
            kv.put("cecha_dzielnicy_id", Integer.toString(d.getCechaDzielnicyId()));
            kv.put("dzielnica_id", Integer.toString(d.getDzielnicaId()));
            kv.put("liczba", Integer.toString(d.getLiczba()));
            inserts.add(SQLQuery.insertSQL("dzielnica_cecha_dzielnicy", kv));
        }
        kv.clear();
        List<Osiedle> osiedle = generateOsiedle(300, 0, dzielnica.size());
        for(Osiedle o: osiedle)
        {
            kv.put("id", Integer.toString(o.getId()));
            kv.put("dzielnica_id", Integer.toString(o.getId()));
            kv.put("nazwa", o.getNazwa());
            inserts.add(SQLQuery.insertSQL("Osiedle", kv));
        }
        kv.clear();
        List<OsiedleCechaOsiedla> osiedleCechaOsiedla = generateOsiedleCechaOsiedla(1000, 0, osiedle.size(), cechaOsiedla.size());
        for(OsiedleCechaOsiedla o: osiedleCechaOsiedla)
        {
            kv.put("cecha_dzielnicy_id", Integer.toString(o.getOsiedleCechaId()));
            kv.put("dzielnica_id", Integer.toString(o.getOsiedleId()));
            kv.put("liczba", Integer.toString(o.getLiczba()));
            inserts.add(SQLQuery.insertSQL("osiedle_cecha_osiedla", kv));
        }
        kv.clear();
        List<Projekt> projekt = generateProjekt(100, 0, dewelopers.size(), osiedle.size());
        for(Projekt p: projekt)
        {
            kv.put("id", Integer.toString(p.getId()));
            kv.put("deweloper_id", Integer.toString(p.getDeweloperId()));
            kv.put("nazwa", p.getNazwa());
            kv.put("opis", p.getOpis());
            kv.put("osiedle_id", Integer.toString(p.getOsiedle_id()));
            kv.put("przewidywana_data_realizacji", p.getPrzewidywanaDataRealizacji().toString());
            kv.put("data_realizacji", p.getDataRealizacji().toString());
            kv.put("data_rozpoczęcia", p.getDataRozpoczecia().toString());
            inserts.add(SQLQuery.insertSQL("projekt", kv));
        }
        kv.clear();
        List<Budynek> budynek = generateBudynek(300, 0, projekt.size());
        for(Budynek b: budynek)
        {
            kv.put("id", Integer.toString(b.getId()));
            kv.put("projekt_id", Integer.toString(b.getProjektId()));
            kv.put("ulica", b.getUlica());
            kv.put("nr_budynku", b.getNrBudynku());
            inserts.add(SQLQuery.insertSQL("budynek", kv));
        }
        kv.clear();
        List<TypMieszkania> typmiesz = generateTypMieszkania(50, 0, budynek.size());
        for(TypMieszkania t: typmiesz)
        {
            kv.put("id", Integer.toString(t.getId()));
            kv.put("ile", Integer.toString(t.getIle()));
            kv.put("klasa", t.getKlasa());
            kv.put("budynek_id", Integer.toString(t.getBudynekId()));
            inserts.add(SQLQuery.insertSQL("typ_mieszkania", kv));
        }
        kv.clear();
        List<Nieruchomosc> nieruchomosc = generateNieruchomosc(500, 0, klients.size(), osiedle.size(), typmiesz.size(), dzielnica, osiedle);
        for(Nieruchomosc n: nieruchomosc)
        {
            kv.put("id", Integer.toString(n.getId()));
            kv.put("id", Integer.toString(n.getNumerWieczysty()));
            kv.put("tytul_nieruchomosci", n.getTytulNieruchomosci());
            kv.put("opis_nieruchomosci", n.getOpisNieruchomosci());
            kv.put("data_stworzenia", n.getDataStworzenia().toString());
            kv.put("klient", Integer.toString(n.getKlient()));
            kv.put("osiedle_id", Integer.toString(n.getOsiedleId()));
            kv.put("dzielnica_id", Integer.toString(n.getDzielnicaId()));
            kv.put("miasto_id", Integer.toString(n.getMiastoId()));
            kv.put("ulica", n.getUlica());
            kv.put("nr_domu", n.getNrDomu());
            kv.put("nr_mieszkania", n.getNrMieszkania());
            kv.put("kod_pocztowy", n.getKodPocztowy());
            kv.put("id", Integer.toString(n.getTypMieszkaniaId()));
            inserts.add(SQLQuery.insertSQL("nieruchomosc", kv));
        }
        kv.clear();
        List<WlasnoscNieruchomosci> wlasnoscNieruchomoscis = generateWlasnoscNieruchomosci(300, 0, cecha.size(), nieruchomosc.size());
        for(WlasnoscNieruchomosci w: wlasnoscNieruchomoscis)
        {
            kv.put("id", Integer.toString(w.getId()));
            kv.put("cecha_id", Integer.toString(w.getCechaId()));
            kv.put("wartosc", Integer.toString(w.getWartosc()));
            kv.put("nieruchomosc_id", Integer.toString(w.getNieruchomoscId()));
            inserts.add(SQLQuery.insertSQL("wlasnosc_nieruchomosci", kv));
        }
        kv.clear();
        List<Oferta> oferta = generateOferta(400, 0, nieruchomosc.size(), oferty.size(), agents.size());
        for(Oferta o: oferta)
        {
            kv.put("id", Integer.toString(o.getId()));
            kv.put("nieruchomosc_id", Integer.toString(o.getId()));
            kv.put("tytul", o.getTytul());
            kv.put("opis", o.getOpis());
            kv.put("cena", o.getCena().toString());
            kv.put("czy_dostepna", (o.isCzyDostepne() ? "True" : "False"));
            kv.put("data_wprowadzenia", o.getDataWprowadzenia().toString());
            kv.put("agent_id", Integer.toString(o.getId()));
            inserts.add(SQLQuery.insertSQL("oferta", kv));

        }
        kv.clear();
        List<Transakcja> transakcjas = generateTransakcja(300, 0, oferta.size(), klients.size(), klients.size());
        for(Transakcja t: transakcjas)
        {
            kv.put("id", Integer.toString(t.getId()));
            kv.put("prowizja", t.getProwizja().toString());
            kv.put("oferta_id", Integer.toString(t.getOfertaId()));
            kv.put("sprzedajacy_id", Integer.toString(t.getSprzedajacyId()));
            kv.put("kupujacy_id", Integer.toString(t.getKupujacyId()));
            inserts.add(SQLQuery.insertSQL("transakcja", kv));

        }
        kv.clear();kv.clear();
        List<Ocena> ocenas = generateOcena(300, 0, transakcjas.size(), transakcjas, oferta);
        for(Ocena o: ocenas)
        {
            kv.put("id", Integer.toString(o.getId()));
            kv.put("wartosc", Integer.toString(o.getWartosc()));
            kv.put("agent_id", Integer.toString(o.getAgentId()));
            kv.put("transakcja_id", Integer.toString(o.getTransakcjaId()));
            inserts.add(SQLQuery.insertSQL("ocena", kv));
        }
        List<Zdarzenie> zdarzenie = generateZdarzenie(300, 0, zdarzenia.size(), transakcjas.size(), transakcjas, oferta);
        for (Zdarzenie z: zdarzenie)
        {
            kv.put("id", Integer.toString(z.getId()));
            kv.put("opinia_klienta", z.getOpiniaKlienta());
            kv.put("agent_id", Integer.toString(z.getAgentId()));
            kv.put("oferta_id", Integer.toString(z.getOfertaId()));
            kv.put("transakcja_id", Integer.toString(z.getTransakcjaId()));
            kv.put("typ_zdarzenia_id", Integer.toString(z.getTypZdarzeniaId()));
            inserts.add(SQLQuery.insertSQL("zdarzenie", kv));
        }
        kv.clear();
        inserts.forEach(System.out::println);
    }

    public String generateRandomString(int length, Mode mode) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String characters = "";

        switch(mode){

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
        }

        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }

    public List<Agent> generateAgent(int number, int startId)
    {
        Random random = new Random();

        List<Agent> agents = new ArrayList<>();
        Fairy fairy = Fairy.create();

        for(int i = startId; i < number + startId ; i++)
        {
            Person person = fairy.person();
            try {
                agents.add(new Agent(i, person.firstName(), person.lastName(), random.nextInt(100000000), generateRandomString(11, Mode.NUMERIC)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agents;
    }

    public List<Adres> generateAdres(int number, int startId)
    {

        List<Adres> adresses = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                adresses.add(new Adres(i, Streets.getStreet(), generateRandomString(3, Mode.NUMERIC), generateRandomString(3, Mode.NUMERIC), generateRandomString(5, Mode.NUMERIC), Cities.getCity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adresses;
    }

    public List<Budynek> generateBudynek(int number, int startId, int proId)
    {
        Random random = new Random();
        List<Budynek> budynek = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                budynek.add(new Budynek(i, random.nextInt(proId), Streets.getStreet(), generateRandomString(4, Mode.NUMERIC)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return budynek;
    }

    public List<Cecha> generateCecha(int number, int startId)
    {
        List<Cecha> cechy = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                cechy.add(new Cecha(i, generateRandomString(30, Mode.ALPHA), i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cechy;
    }

    public List<CechaDzielnicy> generateCechaDzielnicy(int number, int startId)
    {
        List<CechaDzielnicy> cechy = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                cechy.add(new CechaDzielnicy(i, generateRandomString(30, Mode.ALPHA)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cechy;
    }
    public List<CechaOsiedla> generateCechaOsiedla(int number, int startId)
    {
        List<CechaOsiedla> cechy = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                cechy.add(new CechaOsiedla(i, generateRandomString(30, Mode.ALPHA)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cechy;
    }
    public List<Deweloper> generateDeweloper(int number, int startId)
    {
        Random random = new Random();

        List<Deweloper> agents = new ArrayList<>();
        Fairy fairy = Fairy.create();

        for(int i = startId; i < number + startId ; i++)
        {
            Person person = fairy.person();
            try {
                agents.add(new Deweloper(i, fairy.company().name(), Streets.getStreet(),
                        generateRandomString(3, Mode.NUMERIC), generateRandomString(3, Mode.NUMERIC), Cities.getCity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agents;
    }
    public List<Dzielnica> generateDzielnica(int number, int startId, int miastoId)
    {
        Random random = new Random();

        List<Dzielnica> dzielnica = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
                dzielnica.add(new Dzielnica(i, Cities.getCity(), random.nextInt(miastoId)));
        }
        return dzielnica;
    }
    public List<DzielnicaCechaDzielnicy> generateDzielnicaCechaDzielnicy(int number, int startId, int dzielId, int cechId)
    {
        Random random = new Random();

        List<DzielnicaCechaDzielnicy> dzielnicaCechaDzielniciy = new ArrayList<>();


        for(int i = startId; i < number + startId ; i++)
        {
            dzielnicaCechaDzielniciy.add(new DzielnicaCechaDzielnicy(random.nextInt(cechId), random.nextInt(dzielId), random.nextInt(1000)));
        }
        return dzielnicaCechaDzielniciy;
    }
    public List<Klient> generateKlient(int number, int startId, int adrId)
    {
        Random random = new Random();

        List<Klient> agents = new ArrayList<>();
        Fairy fairy = Fairy.create();

        for(int i = startId; i < number + startId ; i++)
        {
            Person person = fairy.person();
            try {
                agents.add(new Klient(i, person.firstName(), person.lastName(), ((person.isMale()) ? "M" : "F"),
                        "US", random.nextInt(adrId), generateRandomString(9, Mode.NUMERIC), person.email()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agents;
    }
    public List<KlientPreferencje> generateKlientPreferencje(int number, int startId, int prefId, int klId)
    {
        Random r = new Random();
        List<KlientPreferencje> pref = new ArrayList<>();
        for(int i = startId; i < number; i++)
        {
            pref.add(new KlientPreferencje(r.nextInt(klId), r.nextInt(prefId)));
        }

        return pref;
    }
    public List<Miasto> generateMiasto(int number, int startId)
    {
        List<Miasto> city = new ArrayList<>();
        for(int i = 0; i < number; i ++)
        {
            city.add(new Miasto(i, Cities.getCity()));
        }
        return city;
    }

    public List<Nieruchomosc> generateNieruchomosc(int number, int startId, int kId, int oId, int tmId, List<Dzielnica> d, List<Osiedle> o)
    {
        Random random = new Random();

        List<Nieruchomosc> nieruchomosc = new ArrayList<>();
        Fairy fairy = Fairy.create();
        int osId;

        for(int i = startId; i < number + startId ; i++)
        {
            osId = random.nextInt(oId);
            Person person = fairy.person();
            try {
                nieruchomosc.add(new Nieruchomosc(i, random.nextInt(), random.nextInt(kId), random.nextInt(osId), o.get(osId).getDzielnicaId(), d.get(o.get(osId).getDzielnicaId()).getMiastoId(),
                        random.nextInt(), new Date(new Date().getTime()), generateRandomString(20, Mode.ALPHA), generateRandomString(20, Mode.ALPHA), Streets.getStreet(),generateRandomString(2, Mode.NUMERIC),
                        generateRandomString(2, Mode.NUMERIC), generateRandomString(5, Mode.NUMERIC)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nieruchomosc;
    }
    public List<Ocena> generateOcena(int number, int startId, int tId, List<Transakcja> t, List<Oferta> o)
    {
        Random random = new Random();

        List<Ocena> ocenas = new ArrayList<>();

        for(int i = startId; i < number + startId ; i++) {
            int val = random.nextInt(tId);
            ocenas.add(new Ocena(i, random.nextInt(10), o.get(t.get(val).getOfertaId()).getAgentId(), val));
        }

        return ocenas;

    }
    public List<Oferta> generateOferta(int number, int startId, int nierId, int toId, int aId)
    {
        Random random = new Random();

        List<Oferta> ofertas = new ArrayList<>();



        for(int i = startId; i < number + startId ; i++)
        {


            try {
                ofertas.add(new Oferta(i, random.nextInt(nierId), random.nextInt(toId), random.nextInt(aId), generateRandomString(20, Mode.ALPHA), generateRandomString(50, Mode.ALPHA), new PGmoney(100000.0), true, new Date(new Date().getTime())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ofertas;
    }
    public List<Osiedle> generateOsiedle(int number, int startId, int dzielId)
    {
        Random random = new Random();

        List<Osiedle> osiedle = new ArrayList<>();


        for(int i = startId; i < number + startId ; i++)
        {

            try {
                osiedle.add(new Osiedle(i, generateRandomString(20, Mode.ALPHA), random.nextInt(dzielId)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return osiedle;
    }
    public List<OsiedleCechaOsiedla> generateOsiedleCechaOsiedla(int number, int startId, int osId, int cechId)
    {
        Random random = new Random();

        List<OsiedleCechaOsiedla> osiedleCechaOsiedlas = new ArrayList<>();


        for(int i = startId; i < number + startId ; i++)
        {
            osiedleCechaOsiedlas.add(new OsiedleCechaOsiedla(random.nextInt(cechId), random.nextInt(osId), random.nextInt(1000)));
        }
        return osiedleCechaOsiedlas;
    }
    public List<Preferencja> generatePreferencja(int number, int startId, int cechaId)
    {
        Random random = new Random();

        List<Preferencja> preferencja = new ArrayList<>();
        Fairy fairy = Fairy.create();

        for(int i = startId; i < number + startId ; i++)
        {
            Person person = fairy.person();
            try {
                preferencja.add(new Preferencja(i, generateRandomString(20, Mode.ALPHA), random.nextInt(100000), random.nextInt(1000000) + 1000000, random.nextInt(cechaId)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return preferencja;
    }
    public List<Projekt> generateProjekt(int number, int startId, int dewId, int osId)
    {
        Random random = new Random();

        List<Projekt> projekt = new ArrayList<>();
        Fairy fairy = Fairy.create();

        for(int i = startId; i < number + startId ; i++)
        {
            Person person = fairy.person();
            try {
                projekt.add(new Projekt(i, random.nextInt(dewId), generateRandomString(20, Mode.ALPHA), generateRandomString(50, Mode.ALPHA), random.nextInt(osId),  new Date(new Date().getTime()),
                       new Date(new Date().getTime()), new Date(new Date().getTime())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return projekt;
    }
    public List<Transakcja> generateTransakcja(int number, int startId, int oId, int sId, int kId)
    {
        Random random = new Random();

        List<Transakcja> transakcja = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {

                transakcja.add(new Transakcja(i, new PGmoney(10000), random.nextInt(oId), random.nextInt(sId), random.nextInt(kId)));
        }
        return transakcja;
    }
    public List<TypMieszkania> generateTypMieszkania(int number, int startId, int buId)
    {
        Random random = new Random();
        List<TypMieszkania> typMieszkania = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {

            try {
                typMieszkania.add(new TypMieszkania(i, random.nextInt(1000), random.nextInt(buId), generateRandomString(10, Mode.ALPHA)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return typMieszkania;
    }
    public List<TypOferty> generateTypOferty(int number, int startId)
    {
        List<TypOferty> typy = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                typy.add(new TypOferty(i, generateRandomString(30, Mode.ALPHA)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return typy;
    }
    public List<TypZdarzenia> generateTypZdarzenia(int number, int startId)
    {
        List<TypZdarzenia> typy = new ArrayList<>();
        for(int i = startId; i < number + startId ; i++)
        {
            try {
                typy.add(new TypZdarzenia(i, generateRandomString(30, Mode.ALPHA)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return typy;
    }
    public List<WlasnoscNieruchomosci> generateWlasnoscNieruchomosci(int number, int startId, int ceId, int nierId)
    {
        Random random = new Random();

        List<WlasnoscNieruchomosci> wlasnoscNieruchomosci = new ArrayList<>();


        for(int i = startId; i < number + startId ; i++)
        {

            try {
                wlasnoscNieruchomosci.add(new WlasnoscNieruchomosci(i, random.nextInt(ceId), random.nextInt(10000), random.nextInt(nierId)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wlasnoscNieruchomosci;
    }
    public List<Zdarzenie> generateZdarzenie(int number, int startId, int tyzId, int tId, List<Transakcja> t, List<Oferta> o)
    {
        Random random = new Random();

        List<Zdarzenie> zdarzenie = new ArrayList<>();


        for(int i = startId; i < number + startId ; i++)
        {
            int val = random.nextInt(tId);
            try {
                zdarzenie.add(new Zdarzenie(i,generateRandomString(50, Mode.ALPHA), o.get(t.get(val).getOfertaId()).getAgentId(), t.get(val).getOfertaId(), val, tyzId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return zdarzenie;
    }
}
