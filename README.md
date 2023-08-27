
# Minacraft Launcher Core Ultimate (MCLCU)

Que es?
MCLCU es una libreria para los launcher de minecraft esta libreria se usa para todo, desde descargar las libreiras asta iniciar el juego, la libreria es opensource i se puede modifica a antojo, la libreria original es esta i esta echa por kender aqui hay una pequeña introducion a como se usa.

<pre>
JsonUtils.readVersions("");
</pre>

usa esto para obtener una lista en forma de array con las versiones a poder descargar, en los "" va la url del manifienso generalmente es:
<pre>
 https://launchermeta.mojang.com/mc/game/version_manifest.json</pre>.

<pre>
JsonUtils.ReadFileVersions("", version);
</pre>

con esto podemos obtener la ulr del json de la version de igula forma necesitremos introdicir el archivo manifest en los "" i la version en version en forma de string.

<pre>
JsonUtils.getJsonVersion(urlManifest);
</pre>

esto nos devuelve el contenido del json de la version para ello necesitamos la url del archivo que se obtenene de la anterror forma.

<pre>
UtilsFiles.createFile(MinecraftPath + "\\versions" + "\\" + version,"json", version, contentJsonVersion);
</pre>

esto nos creara el archivo de la version en una ruta especicicada que en este caso es MinecraftPath (es una variable de la calse constants i nos devuelve la carpeta de .miencraft) aqui estamos enpecicicando que en .minecraft/version/versionselecionada se nos cree un archivo json (la extension se especicica en el segundo parametro) con el mombre del tercer parametro (en este caso es una variable que almazena la version selecionada) i que tenga el contenido del quarto parametro que es el contenido del json que emos leido anterior mente.

<pre>
JsonUtils.readJsonVersion(  MinecraftPath + "\\versions\\" + version + "\\" + version + ".json");
</pre>

esto nos devuelve en forma de string la url del jar de minecraft a desacrgar solo se le necesita dar el Path del json de la version.

<pre>
UtilsFiles.download(urlJarClient,  MinecraftPath + "\\versions\\" + version, version, ".jar");
</pre>

con esto podemos descargar el jar de la version solo necesitamos darle la url del jar que en esta caso esta en la variable urlJarClient, el path de destino, i el nombre que se espcicica en el tercer parametro en este caso es la varsion
[<font color="red"> !!IMPORTANT (es recomendable que tanto como la carpeta como el jar i el josn de la version tengan el nombre de la version selecionada)</font>](#) i la extension .jar que se pone en el quarto parametro.

<pre>
JsonUtils.ReadFileAssetsIndex(  MinecraftPath + "\\versions\\" + version + "\\" + version + ".json");
</pre>

esto lo que hace 
