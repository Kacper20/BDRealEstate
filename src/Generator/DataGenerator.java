package Generator;


import DBKit.SQLQuery;
import Entities.*;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

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
            kv.put("id", new Integer(a.getId()).toString());
            kv.put("nr_agenta", new Integer(a.getNumerAgenta()).toString());
            inserts.add(SQLQuery.insertSQL("Agent", kv));
        }
        kv.clear();
        List<Deweloper> dewelopers = generateDeweloper(10000, 0);
        for(Deweloper d: dewelopers)
        {
            kv.put("id", new Integer(d.getId()).toString());
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
            kv.put("id", new Integer(a.getId()).toString());
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
            kv.put("id", new Integer(m.getId()).toString());
            kv.put("nazwa", m.getNazwa());
            inserts.add(SQLQuery.insertSQL("Miasto", kv));
        }
        kv.clear();
        List<TypOferty> oferty = generateTypOferty(10, 0);
        for(TypOferty t: oferty)
        {
            kv.put("id", new Integer(t.getId()).toString());
            kv.put("nazwa", t.getNazwa());
            inserts.add(SQLQuery.insertSQL("Typ_oferty", kv));
        }
        kv.clear();
        List<TypZdarzenia> zdarzenia = generateTypZdarzenia(10, 0);
        for(TypZdarzenia t: zdarzenia)
        {
            kv.put("id", new Integer(t.getId()).toString());
            kv.put("nazwa", t.getNazwa());
            inserts.add(SQLQuery.insertSQL("Typ_zdarzenia", kv));
        }
        kv.clear();
        List<Cecha> cecha = generateCecha(100, 0);
        for(Cecha c: cecha)
        {
            kv.put("id", new Integer(c.getId()).toString());
            kv.put("nazwa", c.getNazwa());
            kv.put("typ", new Integer(c.getTyp()).toString() );
            inserts.add(SQLQuery.insertSQL("Cecha", kv));
        }
        kv.clear();
        List<CechaOsiedla> cechaOsiedla = generateCechaOsiedla(100, 0);
        for(CechaOsiedla c: cechaOsiedla)
        {
            kv.put("id", new Integer(c.getId()).toString());
            kv.put("nazwa", c.getNazwa());

            inserts.add(SQLQuery.insertSQL("Cecha_osiedla", kv));
        }
        kv.clear();
        List<CechaDzielnicy> cechaDzielnicy = generateCechaDzielnicy(100, 0);
        for(CechaDzielnicy c: cechaDzielnicy)
        {
            kv.put("id", new Integer(c.getId()).toString());
            kv.put("nazwa", c.getNazwa());

            inserts.add(SQLQuery.insertSQL("Cecha_dzielnicy", kv));
        }
        kv.clear();
        List<Klient> klients = generateKlient(10000, 0, adres.size());
        for(Klient k: klients)
        {
            kv.put("id", new Integer(k.getId()).toString());
            kv.put("imie", k.getImie());
            kv.put("nazwisko", k.getNazwisko());
            kv.put("plec", k.getPlec());
            kv.put("obywatelstwo", k.getObywatelstwo());
            kv.put("adres_zamieszkania", new Integer(k.getAdresZamieszkania()).toString());
            kv.put("nr_telefonu", k.getNrTelefonu());
            kv.put("email", k.getEmail());
            inserts.add(SQLQuery.insertSQL("Klient", kv));
        }
        kv.clear();
        List<Preferencja> preferencja = generatePreferencja(1000, 0, cecha.size());
        for(Preferencja p: preferencja)
        {
            kv.put("id", new Integer(p.getId()).toString());
            kv.put("wartosc", p.getWartosc());
            kv.put("od", new Integer(p.getOd()).toString());
            kv.put("do", new Integer(p.getDo()).toString());
            kv.put("cecha_id", new Integer(p.getCechaId()).toString());
            inserts.add(SQLQuery.insertSQL("Preferencja", kv));
        }
        kv.clear();
        List<KlientPreferencje> klientPreferencje = generateKlientPreferencje(1000, 0, preferencja.size(), klients.size());
        for(KlientPreferencje k: klientPreferencje)
        {
            kv.put("klient_id", new Integer(k.getKlientId()).toString());
            kv.put("preferencja", new Integer(k.getPreferencjaId()).toString());
            inserts.add(SQLQuery.insertSQL("Klient_preferencja", kv));
        }
        kv.clear();
        List<Dzielnica> dzielnica = generateDzielnica(300, 0, miasto.size());
        for(Dzielnica d: dzielnica)
        {
            kv.put("id", new Integer(d.getId()).toString());
            kv.put("miasto_id", new Integer(d.getId()).toString());
            kv.put("nazwa", d.getNazwa());
            inserts.add(SQLQuery.insertSQL("Dzielnica", kv));
        }
        kv.clear();
        List<DzielnicaCechaDzielnicy> dzielnicaCechaDzielniciy = generateDzielnicaCechaDzielnicy(1000, 0, dzielnica.size(), cechaDzielnicy.size());
        for(DzielnicaCechaDzielnicy d: dzielnicaCechaDzielniciy)
        {
            kv.put("cecha_dzielnicy_id", new Integer(d.getCechaDzielnicyId()).toString());
            kv.put("dzielnica_id", new Integer(d.getDzielnicaId()).toString());
            kv.put("liczba", new Integer(d.getLiczba()).toString());
            inserts.add(SQLQuery.insertSQL("dzielnica_cecha_dzielnicy", kv));
        }
        kv.clear();
        List<Osiedle> osiedle = generateOsiedle(300, 0, dzielnica.size());
        for(Osiedle o: osiedle)
        {
            kv.put("id", new Integer(o.getId()).toString());
            kv.put("dzielnica_id", new Integer(o.getId()).toString());
            kv.put("nazwa", o.getNazwa());
            inserts.add(SQLQuery.insertSQL("Osiedle", kv));
        }
        kv.clear();
        List<OsiedleCechaOsiedla> osiedleCechaOsiedla = generateOsiedleCechaOsiedla(1000, 0, osiedle.size(), cechaOsiedla.size());
        for(OsiedleCechaOsiedla o: osiedleCechaOsiedla)
        {
            kv.put("cecha_dzielnicy_id", new Integer(o.getOsiedleCechaId()).toString());
            kv.put("dzielnica_id", new Integer(o.getOsiedleId()).toString());
            kv.put("liczba", new Integer(o.getLiczba()).toString());
            inserts.add(SQLQuery.insertSQL("osiedle_cecha_osiedla", kv));
        }
        kv.clear();
        List<Projekt> projekt = generateProjekt(100, 0, dewelopers.size(), osiedle.size());
        for(Projekt p: projekt)
        {
            kv.put("id", new Integer(p.getId()).toString());
            kv.put("deweloper_id", new Integer(p.getDeweloperId()).toString());
            kv.put("nazwa", p.getNazwa());
            kv.put("opis", p.getOpis());
            kv.put("osiedle_id", new Integer(p.getOsiedle_id()).toString());
            kv.put("przewidywana_data_realizacji", p.getPrzewidywanaDataRealizacji().toString());
            kv.put("data_realizacji", p.getDataRealizacji().toString());
            kv.put("data_rozpoczęcia", p.getDataRozpoczecia().toString());
        }





        for(String s: inserts)
        {
            System.out.println(s);
        }
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

//    public List<Budynek> generateBudynek(int number, int startId)
//    {
//
//    }

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
//
//    public List<Nieruchomosc> generateNieruchomosc(int number, int startId)
//    {
//
//    }
//    public List<Ocena> generateOcena(int number, int startId)
//    {
//
//    }
//    public List<Oferta> generateOferta(int number, int startId)
//    {
//
//    }
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
//    public List<Transakcja> generateTransakcja(int number, int startId)
//    {
//
//    }
//    public List<TypMieszkania> generateTypMieszkania(int number, int startId)
//    {
//
//    }
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
//    public List<WlasnoscNieruchomosci> generateWlasnoscNieruchomosci(int number, int startId)
//    {
//
//    }
//    public List<Zdarzenie> generateZdarzenie(int number, int startId)
//    {
//
//    }
}
