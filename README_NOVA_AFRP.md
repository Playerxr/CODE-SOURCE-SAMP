# 📱 Application Launcher Android - NOVA AFRP

Application mobile SA-MP officielle personnalisée pour le serveur **NOVA AFRP (Afrique Francophone Roleplay)**.

---

## ⚙️ Modifications et Configuration appliquées

### 1. Paramètres de Connexion au Serveur
* **Adresse IP** : `51.75.44.14`
* **Port** : `2692`
* **Nom du serveur** : `NOVA AFRP - Afrique Francophone Roleplay`
* Fichier configuré : `app/src/main/java/com/afrp/game/launcher/data/Config.java`

### 2. Identité Visuelle & Icônes
* **Nom de l'application** : `NOVA AFRP`
* **Icônes générées** : Toutes les résolutions mipmap (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`) et l'icône Play Store `512x512` ont été générées automatiquement à partir du logo officiel `logo_afrp.jpg`.
* Fichiers modifiés :
  * `app/src/main/res/values/strings.xml`
  * `app/build.gradle` (`manifestPlaceholders = [appLabel: "NOVA AFRP"]`)
  * `settings.gradle` (`rootProject.name = "NOVA AFRP"`)

### 3. Modpack AFRP Associé
* **Emplacement du modpack** : `c:\Users\junior\Documents\AFRP\AFRP - Afrique Francophone RP\files`
* **Configuration client** : `files/SAMP/settings.json` mis à jour avec `host: 51.75.44.14` et `port: 2692`.
* **Dossier de destination sur le téléphone** : `/Android/data/com.afrp.game/files/`

---

## 🔨 Comment compiler l'APK avec Android Studio

1. **Ouvrir le projet** :
   - Lancez **Android Studio**.
   - Cliquez sur **File > Open** et sélectionnez le dossier :
     `c:\Users\junior\Documents\Source Code Samp v1(2)\CODE SOURCE SAMP`

2. **Synchronisation Gradle** :
   - Laissez Android Studio télécharger les dépendances et synchroniser le projet (Gradle 8.4.0).

3. **Génération de l'APK** :
   - Dans le menu supérieur, cliquez sur **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
   - Une fois la compilation terminée, Android Studio affichera une notification avec un bouton `locate`.
   - L'APK généré se trouvera dans :
     `app/build/outputs/apk/debug/app-debug.apk`

---

## 📲 Installation sur Téléphone Android

1. Transférez et installez l'APK `NOVA AFRP` sur votre téléphone.
2. Avec une application comme **ZArchiver** ou par câble USB :
   - Copiez tout le contenu du dossier `c:\Users\junior\Documents\AFRP\AFRP - Afrique Francophone RP\files`
   - Dans le répertoire Android suivant :
     `/sdcard/Android/data/com.afrp.game/files/`
3. Ouvrez l'application **NOVA AFRP**, entrez votre pseudo RP (ex: `Prenom_Nom`), puis appuyez sur **Jouer** pour vous connecter directement au serveur !
