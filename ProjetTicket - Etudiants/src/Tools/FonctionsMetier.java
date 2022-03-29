/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Etat;
import Entity.Ticket;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier 
{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cnx;
    
    public FonctionsMetier()
    {
        cnx= ConnexionBDD.getCnx();
    }
    public User Verification(String log,String mdp)
    {
        User monUser =null;
        try 
        {
            ps=cnx.prepareStatement("SELECT idUser,nomUser,prenomUser ,statutUser from users WHERE loginUser='"+log+"' AND pwdUser='"+mdp+"'");
            rs=ps.executeQuery();
            if(rs.next())
            {
                monUser= new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
         return monUser;
    }

public ArrayList<Ticket>GetAllTicket()
    {
      
        ArrayList<Ticket>mesTicket = new ArrayList<>();
        try
        {
           ps=cnx.prepareStatement("SELECT idTicket,nomTicket, dateTicket, numEtat FROM tickets");
           rs=ps.executeQuery();
           while(rs.next())
            {
                Ticket monTicket=new Ticket(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
                mesTicket.add(monTicket);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesTicket;
   }
