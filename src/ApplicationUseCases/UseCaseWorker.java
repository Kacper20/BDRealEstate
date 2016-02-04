package ApplicationUseCases;

import DBKit.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aaa on 2016-02-02.
 */
public class UseCaseWorker {
    Connection c;
    public UseCaseWorker() throws SQLException {
        c = ConnectionManager.getInstance().getConn();
    }

    public ObservableList<AgentsController.Agent> getAgentsData(String rate, String money, String transactions) {
        ObservableList<AgentsController.Agent> data = FXCollections.observableArrayList();
        try{
            String SQL = "select a.id, a.nazwisko, a.imie from agent a " +
                    "where ( " +
                    "select avg(oc.wartosc) from ocena oc where a.id = oc.agent_id " +
                    ") >= " + rate + " and ( " +
                    "    select count(*) from oferta of where of.agent_id = a.id and of.cena >= " + money + "::money " +
                    ") >= " + transactions;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                AgentsController.Agent a = new AgentsController.Agent(rs);
                data.add(a);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public void updateProvision(String id) {
        try{
            String SQL = "update transakcja set prowizja = 2*prowizja where id = " + id;
            c.createStatement().executeQuery(SQL);
        } catch(Exception e) {

        }
    }

    public void deleteEvent(String id) {
        try{
            String SQL = "delete from zdarzenie where id = " + id;
            c.createStatement().executeQuery(SQL);
        } catch(Exception e) {

        }
    }

    public ObservableList<TransactionController.Transaction> getTransactionData(String id) {
        ObservableList<TransactionController.Transaction> data = FXCollections.observableArrayList();
        try{
            String SQL = "select t.id, of.tytul, of.cena, t.prowizja  from transakcja t, agent a, oferta of " +
                   " where t.oferta_id = of.id AND of.agent_id = a.id AND a.id = " + id;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                TransactionController.Transaction t = new TransactionController.Transaction(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public ObservableList<OffersController.Offer> getOffersData() {
        ObservableList<OffersController.Offer> data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT of.id, of.tytul, of.opis, (SELECT Count(*) FROM zdarzenie zd WHERE zd.oferta_id = " +
                    "of.id) as Zdarzenia FROM oferta of ORDER BY Zdarzenia DESC";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                OffersController.Offer t = new OffersController.Offer(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public ObservableList<EventsController.Event> getEventsData(String id) {
        ObservableList<EventsController.Event> data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT zd.id, zd.opinia_klienta, a.imie, a.nazwisko " +
                    "FROM zdarzenie zd, oferta of, agent a " +
                    "WHERE zd.oferta_id = " + id +
                    " AND a.id = zd.agent_id ";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                EventsController.Event t = new EventsController.Event(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public ObservableList<MostFamousController.District> getDistrictData(String howMany) {
        ObservableList<MostFamousController.District> data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT dz.nazwa, dz.id, COUNT(*) as Cnt FROM dzielnica dz, nieruchomosc n, oferta of, transakcja t " +
                    "WHERE " +
                    "dz.miasto_id = n.miasto_id AND " +
                    "of.nieruchomosc_id = n.id AND " +
                    "t.oferta_id = of.id " +
                    "GROUP BY dz.nazwa, dz.id ORDER BY Cnt desc LIMIT " + howMany;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                MostFamousController.District t = new MostFamousController.District(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public ObservableList<DistrictSettlementsController.Settlement> getSettlements(String id) {
        ObservableList<DistrictSettlementsController.Settlement> data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT o.id, o.nazwa as Onazwa " +
                    "FROM osiedle o " +
                    "WHERE o.dzielnica_id = " + id;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                DistrictSettlementsController.Settlement t = new DistrictSettlementsController.Settlement(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public ObservableList<DistrictSettlementsController.Attribute> getAllSettlementAttributes() {
        ObservableList<DistrictSettlementsController.Attribute> data = FXCollections.observableArrayList();
        try{
            String SQL = "SELECT co.id, co.nazwa " +
                    "FROM cecha_osiedla co ";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while(rs.next()){
                DistrictSettlementsController.Attribute t = new DistrictSettlementsController.Attribute(rs);
                data.add(t);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return data;
    }

    public void setSettlementAttribute(DistrictSettlementsController.Settlement settlement, DistrictSettlementsController.Attribute attribute) {
        String SQL = "INSERT INTO osiedle_cecha_osiedla " +
                "values ("+ attribute.getId() +", " + settlement.getId() + ", null)";
        try {
            c.createStatement().executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
