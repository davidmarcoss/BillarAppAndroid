package com.info.infomila.david.billarapp.network;

import android.util.Log;

import com.info.infomila.david.billarapp.activities.LoginActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TCPClient {

    private static String SERVER_IP = "192.168.1.150";
    private static int SERVER_PORT = 1237;

    public static void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Soci Login(String nif, String pass) {
        Soci user = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());
            dataSalida.writeInt(1);
            dataSalida.flush();
            dataSalida.writeObject(nif);
            dataSalida.flush();
            dataSalida.writeObject(pass);
            dataSalida.flush();

            String dataServer = null;
            try {
                dataServer = (String) dataEntrada.readObject();

                if (!dataServer.equals("-1")) {
                    LoginActivity.editor.putString("session_id", dataServer);
                    user = (Soci) dataEntrada.readObject();
                }

            } catch (ClassNotFoundException e) {
                socket.close();
            } finally {
                socket.close();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static List<Torneig> getTorneijosOberts() {
        List<Torneig> llTorneig = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeObject("4");
            dataSalida.flush();
            dataSalida.writeObject(LoginActivity.pref.getString("session_id", null));
            dataSalida.flush();

            String dataServer = null;

            try {
                dataServer = (String) dataEntrada.readObject();

                if (!dataServer.equals("-1")) {
                    llTorneig = (List<Torneig>) dataEntrada.readObject();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return llTorneig;
    }

    public static List<Torneig> getTorneijosObertsOnParticipo() {
        List<Torneig> llTorneig = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeObject("3");
            dataSalida.flush();
            dataSalida.writeObject(LoginActivity.pref.getString("session_id", null));
            dataSalida.flush();

            String dataServer = null;

            try {
                dataServer = (String) dataEntrada.readObject();

                if (!dataServer.equals("-1")) {
                    llTorneig = (List<Torneig>) dataEntrada.readObject();
                    Log.e("getListTornServer", llTorneig.size() + "");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return llTorneig;
    }

    public static int ferInscripcio(String sessionID, Integer torneigID) {
        int status = 0;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeObject("5");
            dataSalida.flush();
            dataSalida.writeObject(sessionID);
            dataSalida.flush();
            dataSalida.writeObject(torneigID);
            dataSalida.flush();

            Integer dataServer = null;

            try {
                dataServer = (Integer) dataEntrada.readObject();

                if (dataServer.equals(-1)) {
                    status = -1;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int actualizarSoci(Soci soci) {
        int status = 0;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeObject("2");
            dataSalida.flush();
            dataSalida.writeObject(LoginActivity.pref.getString("session_id", null));
            dataSalida.flush();
            dataSalida.writeObject(soci);
            dataSalida.flush();

            Integer dataServer = null;

            try {
                dataServer = (Integer) dataEntrada.readObject();

                if (dataServer.equals(-1)) {
                    status = -1;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

}
