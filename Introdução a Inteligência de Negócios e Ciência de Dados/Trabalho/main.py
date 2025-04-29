# Importar bibliotecas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Carregar o arquivo CSV
file_path = "dataset/player_statistics_cleaned_final.csv"
df = pd.read_csv(file_path)

# Exibir primeiras linhas
print(df.head())


# Exibir informações gerais do dataset
print('Informações gerais:')
print(df.info())

# Exibir o formato (linhas, colunas)
print('\nFormato do dataset:')
print(f'Linhas: {df.shape[0]}, Colunas: {df.shape[1]}')

# Verificar valores nulos
print('\nQuantidade de valores nulos por coluna:')
print(df.isnull().sum())

# Estatísticas básicas para colunas numéricas
print('Estatísticas descritivas:')
print(df.describe())

# Verificar o nome de todas as colunas
print('Nome das colunas:')
print(df.columns.tolist())

# Analisar a quantidade de valores únicos em cada coluna
print('\nQuantidade de valores únicos por coluna:')
print(df.nunique())

# Analisar a distribuição de uma coluna categórica (exemplo)
# Substitua 'coluna_exemplo' por uma coluna real do seu dataset, como 'platform' ou 'genre'
coluna_exemplo = 'PlayerName'  # ajuste conforme seu dataset
print(f'\nDistribuição da coluna {coluna_exemplo}:')
print(df[coluna_exemplo].value_counts())
