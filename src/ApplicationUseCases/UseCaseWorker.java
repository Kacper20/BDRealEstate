package ApplicationUseCases;

import DBKit.AgentDTO;
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
}
