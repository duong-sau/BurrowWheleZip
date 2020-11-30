import os
import click


@click.command()
@click.option('-p', default=False, help='file input.')
def encode(p):
    os.system('cmd /c "java"')
    pass


if __name__ == '__main__':
    encode()
