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

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Partida;
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

            try {
                int status = dataEntrada.readInt();
                if (status == 1) {
                    LoginActivity.editor.putString("session_id", (String) dataEntrada.readObject());
                    user = (Soci) dataEntrada.readObject();
                } else {

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

    public static List<Torneig> getTornejosOberts(String sessionId) {
        List<Torneig> tornejos = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(2);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();

            try {
                int response = dataEntrada.readInt();
                if (response == 1) {
                    tornejos = (List<Torneig>) dataEntrada.readObject();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tornejos;
    }

    public static List<Torneig> getTornejosOnParticipo(String sessionId) {
        List<Torneig> tornejos = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(3);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();

            try {
                int response = dataEntrada.readInt();
                if (response == 1) {
                    tornejos = (List<Torneig>) dataEntrada.readObject();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tornejos;
    }

    public static boolean ferInscripcio(String sessionID, Integer torneigID) {
        boolean status = false;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(4);
            dataSalida.flush();
            dataSalida.writeObject(sessionID);
            dataSalida.flush();
            dataSalida.writeInt(torneigID);
            dataSalida.flush();

            int response = dataEntrada.readInt();
            if (response == 1) {
                status = true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static boolean updateSoci(Soci soci, String sessionId) {
        boolean status = false;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(5);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();
            dataSalida.writeObject(soci.getNif());
            dataSalida.flush();
            dataSalida.writeObject(soci.getNom());
            dataSalida.flush();
            dataSalida.writeObject(soci.getCognom1());
            dataSalida.flush();
            dataSalida.writeObject(soci.getCognom2());
            dataSalida.flush();
            dataSalida.writeObject(soci.getPasswordHash());
            dataSalida.flush();

            int response = dataEntrada.readInt();
            if (response == 1) {
                status = true;
            } else {
                status = false;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static List<Classificacio> getClassificacio(String sessionId, int sociId, int torneigId, int modalitatId) {
        List<Classificacio> classificacions = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(8);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();
            dataSalida.writeInt(torneigId);
            dataSalida.flush();
            dataSalida.writeInt(modalitatId);
            dataSalida.flush();

            try {
                switch (dataEntrada.readInt()) {
                    case 1:
                        classificacions = (List<Classificacio>) dataEntrada.readObject();
                        break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classificacions;
    }

    public static Boolean SendResultatPartida(String sessionId, Partida mPartida, int sociAId, int sociBId) {
        Boolean status = false;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(7);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();
            dataSalida.writeInt(sociAId);
            dataSalida.flush();
            dataSalida.writeInt(sociBId);
            dataSalida.flush();
            dataSalida.writeObject(mPartida);
            dataSalida.flush();

            int response = dataEntrada.readInt();
            if (response == 1) {
                status = true;
            } else {
                status = false;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  status;
    }

    public static List<Partida> getPartides(String sessionId, int torneigId) {
        List<Partida> partides = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(6);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();
            dataSalida.writeInt(torneigId);
            dataSalida.flush();

            try {
                switch (dataEntrada.readInt()) {
                    case 1:
                        partides = (List<Partida>) dataEntrada.readObject();
                        break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return partides;
    }

    public static Soci GetEstadistiques(String sessionId) {
        Soci user = null;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            ObjectInputStream dataEntrada = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream dataSalida = new ObjectOutputStream(socket.getOutputStream());

            dataSalida.writeInt(9);
            dataSalida.flush();
            dataSalida.writeObject(sessionId);
            dataSalida.flush();

            try {
                int status = dataEntrada.readInt();
                if (status == 1) {
                    user = (Soci) dataEntrada.readObject();
                } else {

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
}
