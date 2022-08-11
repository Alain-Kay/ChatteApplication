Une application de chat Client/Serveur avec Socket java : 

Le serveur java initialise la connexion, il lance l'écoute sur un port et se met en attente des connexions entrantes pour qu'il les accepte.
Java fournit un package java.net qui traite tout ce qui est réseau, on a besoin seulement de deux classes:
    *java.net.ServerSocket: cette classe accepte les connexions venues des clients.
    *java.net.Socket: cette classe permet de se connecter à la machine distante.

On a besoin aussi d'un outil pour saisir, envoyer et recevoir le flux:
    *Scanner: lire les entrées clavier.
    *BufferedReader: lire le texte reçu à partir de l'émetteur.
    *PrintWriter: envoyer le texte saisi.