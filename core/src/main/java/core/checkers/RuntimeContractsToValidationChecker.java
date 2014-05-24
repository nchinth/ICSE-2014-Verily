package core.checkers;

import core.VerilyChainableAction;
import core.VerilyChecker;
import core.VerilyEnv;
import exceptions.VerilyCompileFailedException;
import utils.OpenJMLUtil;

/**
 * Author: John L. Singleton <jsinglet@gmail.com>
 */

import java.io.IOException;

import static core.VerilyChainableAction.*;

public class RuntimeContractsToValidationChecker extends VerilyChecker {

    protected static String checkerName = "Contracts Checker";

    public RuntimeContractsToValidationChecker(){super(checkerName);}

    @Override
    public VerilyChainableAction check(VerilyEnv env, VerilyChainableAction lastCheckerResult) {

        if(env.isEnableContracts()==false){
            return CONTINUE;
        }

        logger.info("[jml] Translating RAC contracts...");
        //

        // run checker.
        //
        try {
            OpenJMLUtil.racCompileProject();
        } catch (InterruptedException e) {
            return OK;
        } catch (IOException | VerilyCompileFailedException e) {
            return getResult(ERROR, e);
        }


        // TODO: Transform JML Comments

        return OK;
    }
}
