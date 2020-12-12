package com.mygdx.chessjava;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RulesSetup {
    
    private static String localFilePath = "data/default.json";
    
    public static Board setBoardFromFile() {
        Board board = null;

        try {
            String absolutePath = new File(localFilePath).getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(absolutePath));

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(br);

            int width = node.get("width").asInt();
            int height = node.get("height").asInt();
            JsonNode jsonBoard = node.get("board");

            board = new Board(width, height);

            int numberOfSquares = width * height;
            for (int square = 0; square < numberOfSquares; square++) {
                int row = square / width;
                int col = square - row * height;
                System.out.println(row + " " + col + " " + jsonBoard.get(square).asText());
                Piece chessPiece = createPieceFromJsonBoard(jsonBoard.get(square).asText());
                if (chessPiece != null) {
                    board.movePiecePosition(chessPiece, col, row);
                }
            }

            board.printBoard();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong while parsing data file: " + localFilePath);
        }

        return board;
    }

    private static Piece createPieceFromJsonBoard(String encodedPiece) {
        Piece chessPiece = null;
        if (!encodedPiece.equals("NN")) {
            char encodedPieceColor = encodedPiece.charAt(0);
            char encodedPieceIdentifier = encodedPiece.charAt(1);

            PieceColor pieceColor = encodedPieceColor == 'B' ? PieceColor.Black : PieceColor.White;

            switch (encodedPieceIdentifier) {
                case 'K':
                    chessPiece = PieceFactory.makeKingPiece(pieceColor);
                    break;
                case 'Q':
                    chessPiece = PieceFactory.makeQueenPiece(pieceColor);
                    break;
                case 'R':
                    chessPiece = PieceFactory.makeRookPiece(pieceColor);
                    break;
                case 'B':
                    chessPiece = PieceFactory.makeBishopPiece(pieceColor);
                    break;
                case 'N':
                    chessPiece = PieceFactory.makeKnightPiece(pieceColor);
                    break;
                case 'P':
                    chessPiece = PieceFactory.makePawnPiece(pieceColor);
                    break;
                default:
                    System.out.println("Invalid piece read from data file: " + encodedPiece);
                    break;
            }
        }
        return chessPiece;
    }
}
