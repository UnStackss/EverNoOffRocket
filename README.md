<p align="center">
  <img src="https://i.imgur.com/m5Nwigv.png" alt="EverCraft" width="150">
</p>

<h1 align="center">NoRocketOffHand Plugin</h1>

<p align="center">
  Un plugin per EverCraft che impedisce ai giocatori di mettere e usare i razzi nella mano secondaria (off-hand).
</p>

---

## 📜 Descrizione

**NoRocketOffHand** è un plugin progettato per il server Minecraft **EverCraft**, che previene l'inserimento e l'uso dei razzi nella mano secondaria (off-hand). Il plugin assicura che i razzi vengano rimossi automaticamente se posizionati nell'off-hand, offrendo una gestione fluida e sicura durante il gameplay.

## 🛠️ Funzionalità

- **Blocca il posizionamento dei razzi nella mano secondaria:** Impedisce ai giocatori di spostare i razzi nell'off-hand.
- **Impedisce l'uso dei razzi dalla mano secondaria:** Blocca l'interazione con i razzi posizionati nell'off-hand.
- **Rimozione automatica dei razzi durante il movimento:** Se un giocatore ha razzi nell'off-hand durante il movimento, questi vengono spostati nell'inventario principale o lasciati cadere se l'inventario è pieno.

## 🚀 Come Installare

1. Scarica il file `.jar` del plugin dalla sezione [Releases](#).
2. Inserisci il file `.jar` nella cartella `plugins` del server EverCraft.
3. Riavvia il server per attivare il plugin.

## ⚙️ Configurazione

Non è richiesta alcuna configurazione aggiuntiva. Il plugin funziona automaticamente una volta installato sul server EverCraft.

## 📄 Come Usarlo

- **Prevenzione Automatica:** Il plugin gestisce automaticamente la prevenzione dei razzi nell'off-hand. Non sono necessari comandi o permessi speciali.
- **Messaggi Informativi:** I giocatori riceveranno messaggi che li informano quando i razzi vengono bloccati o rimossi dalla mano secondaria.

## 📂 Struttura del Codice

Il plugin include i seguenti eventi:

- **`PlayerSwapHandItemsEvent`**: Impedisce il posizionamento dei razzi nella mano secondaria.
- **`PlayerInteractEvent`**: Blocca l'uso dei razzi se posizionati nell'off-hand.
- **`PlayerMoveEvent`**: Rimuove i razzi dalla mano secondaria durante il movimento, spostandoli nell'inventario o lasciandoli cadere.

## 📝 Contribuire

Vuoi contribuire? Fai un fork del repository, crea una nuova branch con le tue modifiche e apri una pull request. Tutte le contribuzioni sono benvenute!

## 🛡️ Licenza

Questo progetto è distribuito sotto la licenza MIT. Consulta il file [LICENSE](LICENSE) per maggiori dettagli.

## 📧 Contatti

Per domande o supporto, contattaci direttamente sul server EverCraft o tramite la sezione Issues su GitHub.
