# Предобученная модель

*Внимание!* Эти файлы занимают очень много места. Поэтому их нужно помещать не в GIT, а в GIT-LFS.

Для этого у вас должен быть установлен пакет `git-lfs`.
```bash
sudo apt install git-lfs
```

В _корне проекта_ нужно отметить те файлы, которые будут попадать в `git-lfs`:
```bash
git lfs install
git lfs track *.onnx_data *.onnx
```
После этого у вас должен появиться файл `<root project dir>/.gitattributes` со следующим содержимым:
```
*.onnx_data filter=lfs diff=lfs merge=lfs -text
*.onnx filter=lfs diff=lfs merge=lfs -text
```

После этого файлы можно добавлять обычным способом, они не будут обрабатываться самим `git`, их будет обслуживать `git-lfs`.
