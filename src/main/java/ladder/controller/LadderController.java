package ladder.controller;

import ladder.controller.dto.LadderRequest;
import ladder.domain.Ladder;
import ladder.domain.Names;
import ladder.factory.PointFactory;
import ladder.view.InputView;
import ladder.view.ResultView;

public class LadderController {

    private final InputView inputView;
    private final ResultView resultView;
    private final PointFactory pointFactory;

    public LadderController(InputView inputView, ResultView resultView, PointFactory pointFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.pointFactory = pointFactory;
    }

    public void run() {
        LadderRequest request = new LadderRequest(inputView.names(), inputView.ladderHeight());

        Names names = request.names();
        Ladder ladder = new Ladder(request.ladderHeight(), names.size(), pointFactory);

        resultView.printResult(names, ladder);
    }

}
