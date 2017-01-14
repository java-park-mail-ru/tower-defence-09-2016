package ru.agrage.project.Mechanics;

/**
 * AGRage Backend Server
 * Данный класс управляет отдельно одной игрой
 */

public class GameContent {
//
//    @NotNull
//    private static final Logger LOGGER = LoggerFactory.getLogger(GameContent.class);
//
//    private Long firstPlayer;
//    private Long secondPlayer;
//
//    private GamePlay gamePlay;
//    private Movement movement;
//
//    private Integer countOfTurns;
//    private Long activePlayerId;
//
//    public GameContent(Long firstPlayer, Long secondPlayer){
//        this.firstPlayer = firstPlayerId;
//        this.secondPlayer = secondPlayerId;
//        this.gamePlay = new GameBoard();
//        this.movement = new Movement();
//        this.countOfTurns = 0;
//    }
//
//    public CoordPair[] getNeighbors(CoordPair cellCord, Long playerId){
//        final Integer playerIdInGame = gameUserIdToGameUserId(playerId);
//        return board.getCellNeighbors(cellCord, playerIdInGame);
//    }
//
//    public CoordPair[] getShipAvailableDirection(Long playerId){
//        final Integer playerIdInGame = gameUserIdToGameUserId(playerId);
//        return board.getShipAvailableDirection(playerIdInGame);
//    }
//
//    public CoordPair getShipCord(Long playerId){
//        final Integer playerInGameId = gameUserIdToGameUserId(playerId);
//        return board.getShipCord(playerInGameId);
//    }
//
//    public Boolean moveShip(CoordPair direction, Long playerId){
//        final Integer playerGameId = gameUserIdToGameUserId(playerId);
//        final Boolean shipMove = board.moveShip(direction, playerGameId);
//        if(shipMove) {
//            ++countOfTurns;
//            changeActivePlayer();
//        }
//        return shipMove;
//    }
//
//    @Nullable
//    public List<Result> movePirat(Integer piratId, CoordPair targetCell, Long playerId){
//        if(!activePlayerId.equals(playerId)){
//            LOGGER.debug("Player try to act in not his own round");
//            return null;
//        }
//        final Integer playerGameId = gameUserIdToGameUserId(playerId);
//        final Integer piratIngameId = piratId + 3 * playerGameId;
//        move = new Movement(piratIngameId, getPiratCord(piratIngameId, playerGameId), targetCell);
//        final List<Result> result = board.movePirat(move, playerGameId); //отдавать один индекс вместо двух
//        if(result.get(0).getStatus()>-1){
//            move = null;
//            ++countOfTurns;
//            changeActivePlayer();
//            return result;
//        } else {
//            move = null;
//            return null;
//        }
//    }
//
//    public CoordPair[] getCellNeighborsWithPirat(Integer piratId, Integer playerId ){
//        return board.getCellNeighborsByPirat(piratId, playerId); //вспомогательный метод, который выдавал окрестности клеток
//    }//по индексу пирата
//
//    public Boolean isCellPlacedNearPirat(Integer piratId, CoordPair targetCell, Integer playerId){
//        return board.isCellPlacedNearPirat(piratId, targetCell, playerId); //вспомогательный метод
//    } //который говорил, является ли клетка смежной с той, в которой стоит пират с заданным индексом
//
//    public CoordPair getPiratCord(Integer piratId, Integer playerId){
//        return board.getPiratCord(piratId, playerId);
//    }
//
//    public Integer getMoveStatus(){
//        return move.getStatus();
//    } // задел на будущее, когда появятся стрелки
//
//    public List<Integer> getMap(){
//        List<Integer> tempList = board.getBoardMap();
//        //String json = new Gson().toJson(tempList);
//        return tempList;
//    }
//
//    public Integer getCountOfTurns() {
//        return countOfTurns;
//    } //количество сделанных ходов за партию
//
//    @Nullable
//    public Long getEnemy(Long playerId) {
//        if(playerId.equals(firstPlayerId)){
//            return secondPlayerId;
//        } else if(playerId.equals(secondPlayerId)){
//            return firstPlayerId;
//        } else {
//            LOGGER.debug("Player who not involved in game try to get enemy id");
//            return null;
//        }
//    }
}